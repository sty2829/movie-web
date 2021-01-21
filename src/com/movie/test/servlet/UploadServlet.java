package com.movie.test.servlet;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;



public class UploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final int FILE_MAX_SIZE = 10 * 1024 * 1024;
	private final int TOTAL_MAX_SIZE = FILE_MAX_SIZE * 10;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		boolean check = ServletFileUpload.isMultipartContent(request);
		System.out.println("요청 객체가 파일 전송 할수있는애 마즘? : " + check);
		if(!check) {
			throw new ServletException("form태그의 enctype을 확인해주세요.");
		}
		DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
		ServletFileUpload servletFileUpload = new ServletFileUpload(diskFileItemFactory);
		servletFileUpload.setFileSizeMax(FILE_MAX_SIZE);
		servletFileUpload.setSizeMax(TOTAL_MAX_SIZE);
		try {
			List<FileItem> fileList = servletFileUpload.parseRequest(request);
			for(int i=0; i<fileList.size(); i++) {
				FileItem fileItem = fileList.get(i);
				String tagName = fileItem.getFieldName();
				if(fileItem.isFormField()) {
					System.out.println(tagName + " <- 임마는 FormFiled임");
					String value = fileItem.getString("UTF-8");
					System.out.println("값 : " + value);
				}else {
					System.out.println(tagName + " <- 임마는 FormFiled 아니고 FileFiled임");
					String fileName = fileItem.getName();
					System.out.println("업로드한 파일이름 : " + fileName);
					File targetFile = new File("C:\\study\\workspace\\movie-web\\WebContent\\" + fileName);
					fileItem.write(targetFile);
				}
			}
		} catch (FileUploadException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		doGet(request, response);
	}

}
