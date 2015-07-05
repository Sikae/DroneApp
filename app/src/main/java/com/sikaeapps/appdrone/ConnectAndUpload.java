package com.sikaeapps.appdrone;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.SocketException;
import java.net.UnknownHostException;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

import android.util.Log;
import android.widget.Toast;

public class ConnectAndUpload {
	public static void connnectingwithFTP() {  
        boolean status = false;
        FTPClient mFtpClient = new FTPClient();  
        try {
             
             mFtpClient.setConnectTimeout(300); //originally: 10*1000  in seconds
             mFtpClient.connect("ftp.cacaobits.com", 21);    // Direccion fija
             status = mFtpClient.login("appdrone@cacaobits.com", "Appdrone7");      // Usuario y pass fijos
             Log.e("isFTPConnected", String.valueOf(status));  
             if (FTPReply.isPositiveCompletion(mFtpClient.getReplyCode())) {  
                  mFtpClient.setFileType(FTP.BINARY_FILE_TYPE);    // nO cambiar a FTP.ASCII_FILE_TYPE 
                  mFtpClient.enterLocalPassiveMode();  
                  FTPFile[] mFileArray = mFtpClient.listFiles();  
                  Log.e("Size", String.valueOf(mFileArray.length));  
             }  
        } catch (SocketException e) {  
             e.printStackTrace();  
        } catch (UnknownHostException e) {  
             e.printStackTrace();  
        } catch (IOException e) {  
             e.printStackTrace();  
        }  
        
       
        try {  
       	 
            FileInputStream srcFileStreamX = new FileInputStream(new File("data/data/com.sikaeapps.appdrone/files/x_n_ref.txt"));
            boolean status1 = mFtpClient.storeFile("x_n_ref.txt", srcFileStreamX);  
            srcFileStreamX = new FileInputStream(new File("data/data/com.sikaeapps.appdrone/files/x_np1_ref.txt"));
            status1 = mFtpClient.storeFile("x_np1_ref.txt", srcFileStreamX); 
            srcFileStreamX = new FileInputStream(new File("data/data/com.sikaeapps.appdrone/files/y_n_ref.txt"));
            status1 = mFtpClient.storeFile("y_n_ref.txt", srcFileStreamX); 
            srcFileStreamX = new FileInputStream(new File("data/data/com.sikaeapps.appdrone/files/y_np1_ref.txt"));
            status1 = mFtpClient.storeFile("y_np1_ref.txt", srcFileStreamX);
            srcFileStreamX = new FileInputStream(new File("data/data/com.sikaeapps.appdrone/files/z_n_ref.txt"));
            status1 = mFtpClient.storeFile("z_n_ref.txt", srcFileStreamX);
            srcFileStreamX = new FileInputStream(new File("data/data/com.sikaeapps.appdrone/files/z_np1_ref.txt"));
            status1 = mFtpClient.storeFile("z_np1_ref.txt", srcFileStreamX);
            Log.e("Status", String.valueOf(status1));  
            srcFileStreamX.close();  
            
            
            
       } catch (Exception e) {  
            e.printStackTrace();  
       }  
        /////
        Log.d("SIKAE", "LISTO UPLOAD");
        
   }
	
	
	public void uploadFile(FTPClient ftpClient, File downloadFile,String serverfilePath) {  
        try {  
             FileInputStream srcFileStream = new FileInputStream(downloadFile);  
             boolean status = ftpClient.storeFile("remote ftp path",  
                       srcFileStream);  
             Log.e("Status", String.valueOf(status));  
             srcFileStream.close();  
        } catch (Exception e) {  
             e.printStackTrace();  
        }  
   }

}
