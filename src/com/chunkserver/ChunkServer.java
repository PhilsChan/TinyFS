package com.chunkserver;

import com.interfaces.ChunkServerInterface;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
/**
 * implementation of interfaces at the chunkserver side
 * 
 * @author Shahram Ghandeharizadeh
 *
 */

public class ChunkServer implements ChunkServerInterface {
	final static String filePath = ".\\file\\"; // or C:\\newfile.txt
	public static long counter;

	/**
	 * Initialize the chunk server
	 */
	public ChunkServer() {
//		System.out.println(
//				"Constructor of ChunkServer is invoked:  Part 1 of TinyFS must implement the body of this method.");
		counter = 0;
//		System.out.println("It does nothing for now.\n");
	}

	/**
	 * Each chunk corresponds to a file. Return the chunk handle of the last chunk
	 * in the file.
	 */
	public String initializeChunk() {
//		System.out.println("createChunk invoked:  Part 1 of TinyFS must implement the body of this method.");
		String handle = counter + ".file";
		counter++;
		File file = new File(filePath+handle);
		File dir = new File(filePath);
		try {
			dir.mkdirs();
			file.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
//		System.out.println("Returns null for now.\n");
		return handle;
	}

	/**
	 * Write the byte array to the chunk at the specified offset The byte array size
	 * should be no greater than 4KB
	 */
	public boolean putChunk(String ChunkHandle, byte[] payload, int offset) {
//		System.out.println("writeChunk invoked:  Part 1 of TinyFS must implement the body of this method.");
		try {
			FileOutputStream fos = new FileOutputStream(filePath+ChunkHandle);
			fos.write(payload, offset, payload.length);
			fos.close();
		}
		catch (IOException e) {
			e.printStackTrace();
			return false;
		}
//		System.out.println("Returns false for now.\n");
		return true;
	}

	/**
	 * read the chunk at the specific offset
	 */
	public byte[] getChunk(String ChunkHandle, int offset, int NumberOfBytes) {
//		System.out.println("readChunk invoked:  Part 1 of TinyFS must implement the body of this method.");
		byte[] ans = new byte[NumberOfBytes];
		try {
			FileInputStream fis = new FileInputStream(filePath+ChunkHandle);
			fis.skip(offset);
			fis.read(ans,0,NumberOfBytes);
			fis.close();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
//		System.out.println("Returns null for now.\n");
		return ans;
	}

}
