package gov.nist.healthcare.tools.hl7.v2.xml;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class PDFGeneratorTool {

	public void unZipIt(String zipFile, String outputFolder) {
		byte[] buffer = new byte[1024];

		try {

			// create output directory is not exists
			File folder = new File(outputFolder);
			if (!folder.exists()) {
				folder.mkdir();
			}

			// get the zip file content
			ZipInputStream zis = new ZipInputStream(new FileInputStream(zipFile));
			// get the zipped file list entry
			ZipEntry ze = zis.getNextEntry();

			while (ze != null) {

				String fileName = ze.getName();
				File newFile = new File(outputFolder + File.separator + fileName);

				System.out.println("file unzip : " + newFile.getAbsoluteFile());

				// create all non exists folders
				// else you will hit FileNotFoundException for compressed folder
				new File(newFile.getParent()).mkdirs();

				FileOutputStream fos = new FileOutputStream(newFile);

				int len;
				while ((len = zis.read(buffer)) > 0) {
					fos.write(buffer, 0, len);
				}

				fos.close();
				ze = zis.getNextEntry();
			}

			zis.closeEntry();
			zis.close();

			System.out.println("Done");

		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	public void zipIt(String SOURCE_FOLDER,  String zipFile) {
		List<String> fileList = new ArrayList<String>();
		this.generateFileList(new File(SOURCE_FOLDER), fileList, SOURCE_FOLDER);
		byte[] buffer = new byte[1024];

		try {

			FileOutputStream fos = new FileOutputStream(zipFile);
			ZipOutputStream zos = new ZipOutputStream(fos);

			for (String file : fileList) {

				System.out.println("File Added : " + file);
				ZipEntry ze = new ZipEntry(file);
				zos.putNextEntry(ze);

				FileInputStream in = new FileInputStream(SOURCE_FOLDER + File.separator + file);

				int len;
				while ((len = in.read(buffer)) > 0) {
					zos.write(buffer, 0, len);
				}

				in.close();
			}

			zos.closeEntry();
			// remember close it
			zos.close();

			System.out.println("Done");
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	public void generateFileList(File node, List<String> fileList, String SOURCE_FOLDER) {

		// add file only
		if (node.isFile()) {
			fileList.add(generateZipEntry(node.getAbsoluteFile().toString(), SOURCE_FOLDER));
		}

		if (node.isDirectory()) {
			String[] subNote = node.list();
			for (String filename : subNote) {
				generateFileList(new File(node, filename), fileList, SOURCE_FOLDER);
			}
		}

	}
	
	private String generateZipEntry(String file, String SOURCE_FOLDER){
    	return file.substring(SOURCE_FOLDER.length()+1, file.length());
    }

	public void gen(String source) {
		File dir = new File(source);
		File[] fileList = dir.listFiles();
		try {
			for (int i = 0; i < fileList.length; i++) {
				File file = fileList[i];
				if (file.isFile()) {
					if (file.getName().endsWith(".html")) {

						if (file.getName().equals("TestStory.html")) {

						} else if (file.getName().equals("TestStoryPDF.html")) {
							String htmlFileName = file.getAbsolutePath();
							String pdfFileName = file.getAbsolutePath().replace("PDF.html", ".pdf");
							genPDF(htmlFileName, pdfFileName);
							file.delete();
						} else if (file.getName().equals("MessageContentPDF.html")) {
							String htmlFileName = file.getAbsolutePath();
							String pdfFileName = file.getAbsolutePath().replace("PDF.html", ".pdf");
							genPDF(htmlFileName, pdfFileName);
							file.delete();
						} else if (file.getName().equals("TestDataSpecificationPDF.html")) {
							String htmlFileName = file.getAbsolutePath();
							String pdfFileName = file.getAbsolutePath().replace("PDF.html", ".pdf");
							genPDF(htmlFileName, pdfFileName);
							file.delete();
						} else if (file.getName().equals("TestPackage.html")) {
							String htmlFileName = file.getAbsolutePath();
							String pdfFileName = file.getAbsolutePath().replace(".html", ".pdf");
							String coverPageName = htmlFileName.replace("TestPackage.html", "CoverPage.html");
							genTOCoverPagePDF(htmlFileName, pdfFileName, coverPageName);
						} else {
							String htmlFileName = file.getAbsolutePath();
							String pdfFileName = file.getAbsolutePath().replace(".html", ".pdf");
							genPDF(htmlFileName, pdfFileName);
						}
					}
				} else if (file.isDirectory()) {
					gen(file.getCanonicalPath().toString());
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void genPDF(String htmlFileName, String pdfFileName) throws IOException {
		ProcessBuilder pb = new ProcessBuilder("/usr/local/bin/wkhtmltopdf", htmlFileName, pdfFileName);
		pb.redirectErrorStream(true);
		Process process = pb.start();
		BufferedReader inStreamReader = new BufferedReader(new InputStreamReader(process.getInputStream()));

		String line = inStreamReader.readLine();

		while (line != null) {
			System.out.println(line);
			line = inStreamReader.readLine();
		}
	}

	private void genTOCPDF(String htmlFileName, String pdfFileName) throws IOException {
		ProcessBuilder pb = new ProcessBuilder("/usr/local/bin/wkhtmltopdf", "toc", htmlFileName, pdfFileName);
		pb.redirectErrorStream(true);
		Process process = pb.start();
		BufferedReader inStreamReader = new BufferedReader(new InputStreamReader(process.getInputStream()));

		String line = inStreamReader.readLine();

		while (line != null) {
			System.out.println(line);
			line = inStreamReader.readLine();
		}
	}

	private void genTOCoverPagePDF(String htmlFileName, String pdfFileName, String coverpageFileName)
			throws IOException {
		ProcessBuilder pb = new ProcessBuilder("/usr/local/bin/wkhtmltopdf", "cover", coverpageFileName, "toc",
				htmlFileName, pdfFileName);
		pb.redirectErrorStream(true);
		Process process = pb.start();
		BufferedReader inStreamReader = new BufferedReader(new InputStreamReader(process.getInputStream()));

		String line = inStreamReader.readLine();

		while (line != null) {
			System.out.println(line);
			line = inStreamReader.readLine();
		}
	}

}
