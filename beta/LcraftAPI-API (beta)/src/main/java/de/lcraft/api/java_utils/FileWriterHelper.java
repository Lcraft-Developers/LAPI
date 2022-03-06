package de.lcraft.api.java_utils;

import org.checkerframework.checker.units.qual.A;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Objects;

public class FileWriterHelper {

	private File file;
	private FileWriter fileWriter;

	public FileWriterHelper(File file) throws IOException {
		this.file = file;
		resetFileWriter();
	}

	public void resetFileWriter() throws IOException {
		this.fileWriter = new FileWriter(getFile(), true);
	}
	public void addLine(String line) throws IOException {
		getFileWriter().write(line + System.lineSeparator());
	}
	public void removeAllLines() throws IOException {
		this.fileWriter = new FileWriter(getFile(), false);
		getFileWriter().write("");
		getFileWriter().flush();
		resetFileWriter();
	}
	public ArrayList<String> getAllLines() throws IOException {
		ArrayList<String> allLines = new ArrayList<>();
		if(Objects.nonNull(getFile()) && getFile().exists()) {
			try (BufferedReader br = new BufferedReader(new FileReader(file))) {
				String line;
				while ((line = br.readLine()) != null) {
					allLines.add(line);
				}
			}
		}
		return allLines;
	}

	public File getFile() {
		return file;
	}
	public FileWriter getFileWriter() {
		return fileWriter;
	}
	public void close() throws IOException {
		getFileWriter().flush();
		getFileWriter().close();
	}

}
