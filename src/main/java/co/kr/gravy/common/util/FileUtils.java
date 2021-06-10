package co.kr.gravy.common.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.apache.commons.codec.binary.Base64;

public class FileUtils {

	/**
	 * @return
	 * @throws Exception
	 */
	public static Path createDiretorys(Path path) throws Exception{
		if (!Files.isDirectory(path.getParent())) {
			Files.createDirectories(path.getParent());
		}
		return path;
	}
	
	
	/**
	 * Base64 
	 * @throws Exception
	 */
	public static Path fileWriteBase64(Path path, String base64) throws Exception {
		createDiretorys(path);
		System.out.println(path.toString());
		byte[] output_file = Base64.decodeBase64(base64);
		Path writePath =  Files.write(path, output_file);
		return writePath;
	}
	
	
	/**
	 * Base64 
	 * @throws Exception
	 */
	public static String fileReadBase64(Path path) throws Exception {
		String base64 = Base64.encodeBase64String(Files.readAllBytes(path));
		return base64;
	}
	
	
	/**
	 * 원본 파일을 카피 한다.
	 * @param source
	 * @param dest
	 * @throws IOException
	 */
	public static Path fileCopy(String source, String dest) throws IOException { 
		System.out.println("source : " + source);
		System.out.println("dest : " + dest);
		Path sourceDirectory = Paths.get(source);
	    Path targetDirectory = Paths.get(dest);
	    //Path path =Files.copy(new File(source).toPath(), new File(dest).toPath(), StandardCopyOption.REPLACE_EXISTING);
	    return  Files.copy(sourceDirectory, targetDirectory, StandardCopyOption.REPLACE_EXISTING);
	    
	}
	
	/**
	 * 원본 파일을 이동 한다.
	 * @param source
	 * @param dest
	 * @throws IOException
	 */
	public static Path fileMove(String source, String dest) throws IOException { 
		System.out.println("source : " + source);
		System.out.println("dest : " + dest);
		Path sourceDirectory = Paths.get(source);
	    Path targetDirectory = Paths.get(dest);
	    //Path path =Files.copy(new File(source).toPath(), new File(dest).toPath(), StandardCopyOption.REPLACE_EXISTING);
	    return Files.move(sourceDirectory, targetDirectory, StandardCopyOption.REPLACE_EXISTING);
	    
	}
	
	
	/**
	 * 원본 파일을 이동 한다.
	 * @param source
	 * @param dest
	 * @throws IOException
	 */
	public static void fileDelete(Path path) throws IOException { 
		Files.delete(path);
	}
	
	
	/**
	 * 특정 폴더를 생성한다.
	 * @param directoryPath
	 * @throws IOException
	 */
	public static Path createDirectory(Path directoryPath)throws IOException {
		return Files.createDirectory(directoryPath);
	}
	
	/**
	 * 특정 폴더를 생성한다.
	 * @param directoryPath
	 * @throws IOException
	 */
	public static Path createDirectorys(Path directoryPath)throws IOException {
		return Files.createDirectories(directoryPath);
	}

}
