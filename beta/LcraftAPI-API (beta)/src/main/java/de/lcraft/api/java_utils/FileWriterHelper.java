package de.lcraft.api.java_utils;

import org.checkerframework.checker.units.qual.A;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;

public class FileWriterHelper {

	private File file;
	private FileWriter fileWriter;

	public FileWriterHelper(File file) throws IOException {
		this.file = file;
		this.fileWriter = new FileWriter(getFile(), true);
	}

	public void addLine(String line) throws IOException {
		getFileWriter().write(line + System.lineSeparator());
		getFileWriter().flush();
	}
	public void removeAllLines() throws IOException {
		fileWriter = new FileWriter(getFile(), false);
		getFileWriter().write("");
		getFileWriter().flush();
		getFileWriter().close();
		fileWriter = new FileWriter(getFile(), true);
	}
	public ArrayList<String> getAllLines() throws IOException {
		ArrayList<String> allLines = new ArrayList<>();
		allLines.addAll(Files.readAllLines(getFile().toPath()));
		return allLines;
	}

	public File getFile() {
		return file;
	}
	public FileWriter getFileWriter() {
		return fileWriter;
	}

}
