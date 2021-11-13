package de.lcraft.api.utils.minecraft.spigot.manager.classloaders;

import com.google.common.io.ByteStreams;
import de.lcraft.api.utils.minecraft.spigot.module.ModuleDescriptionFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLClassLoader;
import java.security.CodeSigner;
import java.security.CodeSource;
import java.util.ArrayList;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.jar.Manifest;

public class ModuleClassLoader extends URLClassLoader {

    static
    {
        ClassLoader.registerAsParallelCapable();
    }

    private JarFile jar;
    private URL url;
    private Manifest manifest;
    public static ArrayList<ClassLoader> classLoaders = new ArrayList<>();

    public ModuleClassLoader(ModuleDescriptionFile file) throws IOException {
        this(file.getFile());
    }

    public ModuleClassLoader(File file) throws IOException {
        super(new URL[]
                {
                        file.toURI().toURL()
                } );
        jar = new JarFile(file);
        this.url = file.toURI().toURL();
        classLoaders.add(this);
        this.manifest = jar.getManifest();
    }


    @Override
    protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException
    {
        return loadClass0( name, resolve, true, true );
    }
    private Class<?> loadClass0(String name, boolean resolve, boolean checkOther, boolean checkLibraries) throws ClassNotFoundException
    {
        try
        {
            Class<?> result = super.loadClass( name, resolve );

            // Library classes will appear in the above, but we don't want to return them to other plugins
            if ( checkOther || result.getClassLoader() == this )
            {
                return result;
            }
        } catch ( ClassNotFoundException ex )
        {
        }

        /*if ( checkLibraries && libraryLoader != null )
        {
            try
            {
                return this.loadClass( name );
            } catch ( ClassNotFoundException ex )
            {
            }
        }*/

        if ( checkOther )
        {
            for (ClassLoader loader : classLoaders )
            {
                if ( loader != this )
                {
                    try
                    {
                        return loader.loadClass(name);
                    } catch ( ClassNotFoundException ex )
                    {
                    }
                }
            }
        }

        throw new ClassNotFoundException( name );
    }
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException
    {
        String path = name.replace( '.', '/' ).concat( ".class" );
        JarEntry entry = jar.getJarEntry( path );

        if ( entry != null )
        {
            byte[] classBytes;

            try ( InputStream is = jar.getInputStream( entry ) )
            {
                classBytes = ByteStreams.toByteArray( is );
            } catch ( IOException ex )
            {
                throw new ClassNotFoundException( name, ex );
            }

            int dot = name.lastIndexOf( '.' );
            if ( dot != -1 )
            {
                String pkgName = name.substring( 0, dot );
                if ( getPackage( pkgName ) == null )
                {
                    try
                    {
                        if ( manifest != null )
                        {
                            definePackage( pkgName, manifest, url );
                        } else
                        {
                            definePackage( pkgName, null, null, null, null, null, null, null );
                        }
                    } catch ( IllegalArgumentException ex )
                    {
                        if ( getPackage( pkgName ) == null )
                        {
                            throw new IllegalStateException( "Cannot find package " + pkgName );
                        }
                    }
                }
            }

            CodeSigner[] signers = entry.getCodeSigners();
            CodeSource source = new CodeSource( url, signers );

            return defineClass( name, classBytes, 0, classBytes.length, source );
        }

        return super.findClass( name );
    }

    public static ArrayList<ClassLoader> getClassLoaders() {
        return classLoaders;
    }
    public JarFile getJar() {
        return jar;
    }
    public Manifest getManifest() {
        return manifest;
    }
    public URL getUrl() {
        return url;
    }
    @Override
    public URL[] getURLs() {
        return super.getURLs();
    }

}
