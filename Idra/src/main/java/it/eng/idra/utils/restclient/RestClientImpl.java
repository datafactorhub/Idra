/*******************************************************************************
 * Idra - Open Data Federation Platform
 *  Copyright (C) 2020 Engineering Ingegneria Informatica S.p.A.
 *  
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * at your option) any later version.
 *  
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *  
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/
package it.eng.idra.utils.restclient;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.util.Map;

import javax.ws.rs.core.MediaType;

import org.apache.http.HttpResponse;

import com.sun.research.ws.wadl.HTTPMethods;

public class RestClientImpl extends RestClientBaseImpl implements RestClient {
	
	public HttpResponse sendGetRequest(String urlString, Map<String, String> headers) 
			throws MalformedURLException {
		return invoke(HTTPMethods.GET, urlString, headers, null, null);
	}
	
	public HttpResponse sendPostRequest(String urlString, String data, MediaType type, Map<String, String> headers) 
			throws MalformedURLException {
		
		return invoke(HTTPMethods.POST, urlString, headers, type, data);
	}
	
	public HttpResponse sendDeleteRequest(String urlString, Map<String, String> headers) 
			throws MalformedURLException {
		return invoke(HTTPMethods.DELETE, urlString, headers, null, null);
	}
	
	public HttpResponse sendPutRequest(String urlString, String data, MediaType type, Map<String, String> headers) 
			throws MalformedURLException {
		
		return invoke(HTTPMethods.PUT, urlString, headers, type, data);
	}
	
	public HttpResponse sendHeadRequest(String urlString, Map<String, String> headers) 
			throws MalformedURLException {
		
		return invoke(HTTPMethods.HEAD, urlString, headers, null, null);
	}
	
	@SuppressWarnings("deprecation")
	public String getHttpResponseBody(HttpResponse httpresponse) 
			throws Exception {
		
		BufferedReader rd = new BufferedReader(new InputStreamReader(httpresponse.getEntity().getContent()));

		StringBuffer result = new StringBuffer();
		String line = "";
		while ((line = rd.readLine()) != null) {
			result.append(line);
		}
		
		httpclient.getConnectionManager().shutdown();
		
		return result.toString();
	}
	
	public int getStatus(HttpResponse httpresponse) {
		return httpresponse.getStatusLine().getStatusCode();
	}

}
