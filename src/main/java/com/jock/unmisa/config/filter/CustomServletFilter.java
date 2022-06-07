package com.jock.unmisa.config.filter;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import javax.servlet.FilterChain;
import javax.servlet.ReadListener;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import lombok.extern.slf4j.Slf4j;

@Slf4j
//@Component
public class CustomServletFilter extends OncePerRequestFilter{

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
		//before
		filterChain.doFilter(new XssFilterInter(request), response);
		//after
	}

	protected static class XssFilterInter extends HttpServletRequestWrapper {

		private byte[] body;

		public XssFilterInter(HttpServletRequest request) {
			super(request);
			try {
				InputStream is = request.getInputStream();
				if (is != null) {
					StringBuffer sb = new StringBuffer();
					while(true) {
						int data = is.read();
						if (data < 0) break;
						sb.append((char) data);
					}

					String result = doWork(sb.toString());
					body = result.getBytes(StandardCharsets.UTF_8);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
		@Override
		public ServletInputStream getInputStream() throws IOException {
			final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(this.body);
			return new ServletInputStream() {
				@Override
				public int read() throws IOException {
					return byteArrayInputStream.read();
				}

				@Override
				public boolean isFinished() {
					// TODO Auto-generated method stub
					return false;
				}

				@Override
				public boolean isReady() {
					// TODO Auto-generated method stub
					return false;
				}

				@Override
				public void setReadListener(ReadListener listener) {
					// TODO Auto-generated method stub
					
				}
			};
		}



		private String doWork(String job) {
			StringBuffer sb = new StringBuffer();
  			for (int i = 0; i < job.length(); i++) {
				switch (job.charAt(i)) {
					case '<':
						sb.append("&lt;"); break;
					case '>':
						sb.append("&gt;"); break;
					case '(':
						sb.append("&#40;"); break;
					case ')':
						sb.append("&#41;"); break;
					case '#':
						sb.append("&#35;"); break;
					case '$':
						sb.append("&#39;"); break;
					default:
						sb.append(job.charAt(i));
				}
			}
			return sb.toString();
		}
	}

}