package com.turkcell.playcell.gamingplatform.api.util;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Base64;

public class GenerateTicket {
	
	public static String CreateStaticTicketwithIP(String url, String clientip, long second, String privatekey) 
	throws URISyntaxException, NoSuchAlgorithmException, UnsupportedEncodingException 
	{
        LocalDateTime now = LocalDateTime.ofInstant(Instant.now(), ZoneId.systemDefault()).plusSeconds(second);

        LocalDateTime epoch = LocalDateTime.ofInstant(Instant.EPOCH, ZoneId.systemDefault());
        long seconds = Duration.between(epoch, now).getSeconds();
        String timestr = String.valueOf(seconds);

        URI uri = new URI(url);
        String relativeurl = uri.getPath();

        String key = privatekey + relativeurl + timestr + clientip;
        MessageDigest md5Hasher = MessageDigest.getInstance("MD5");
        byte[] hashedDataBytes = md5Hasher.digest(key.getBytes("ASCII"));

        String hash = Base64.getEncoder().encodeToString(hashedDataBytes);
        hash = hash.replace('+', '-').replace('/', '_').replace("=", "");

        String returnurl = AddQuerryParameter2URL(url, "st", hash);

        returnurl = AddQuerryParameter2URL(returnurl, "e", timestr);
        return returnurl;
    }
	
	public static String CreateStaticTicket(String url, long second, String privatekey) 
	throws URISyntaxException, NoSuchAlgorithmException, UnsupportedEncodingException 
	{
        LocalDateTime now = LocalDateTime.ofInstant(Instant.now(), ZoneId.systemDefault()).plusSeconds(second);

        LocalDateTime epoch = LocalDateTime.ofInstant(Instant.EPOCH, ZoneId.systemDefault());
        long seconds = Duration.between(epoch, now).getSeconds();
        String timestr = String.valueOf(seconds);

        URI uri = new URI(url);
        String relativeurl = uri.getPath();

        String key = privatekey + relativeurl + timestr ;
        MessageDigest md5Hasher = MessageDigest.getInstance("MD5");
        byte[] hashedDataBytes = md5Hasher.digest(key.getBytes("ASCII"));

        String hash = Base64.getEncoder().encodeToString(hashedDataBytes);
        hash = hash.replace('+', '-').replace('/', '_').replace("=", "");

        String returnurl = AddQuerryParameter2URL(url, "st", hash);

        returnurl = AddQuerryParameter2URL(returnurl, "e", timestr);
        return returnurl;
    }

    public static String CreateUrlTicket(String url, long second, String privatekey) 
	throws URISyntaxException, NoSuchAlgorithmException, UnsupportedEncodingException 
	{
        LocalDateTime now = LocalDateTime.ofInstant(Instant.now(), ZoneId.systemDefault()).plusSeconds(second);

        LocalDateTime epoch = LocalDateTime.ofInstant(Instant.EPOCH, ZoneId.systemDefault());
        long seconds = Duration.between(epoch, now).getSeconds();
        String timestr = String.valueOf(seconds);

        URI uri = new URI(url);
        String relativeurl = uri.getPath().substring(0, uri.getPath().lastIndexOf('/'));

        String key = privatekey + relativeurl + timestr ;
        MessageDigest md5Hasher = MessageDigest.getInstance("MD5");
        byte[] hashedDataBytes = md5Hasher.digest(key.getBytes("ASCII"));

        String hash = Base64.getEncoder().encodeToString(hashedDataBytes);
        hash = hash.replace('+', '-').replace('/', '_').replace("=", "");

        String returnurl = AddQuerryParameter2URL(url, "st", hash);

        returnurl = AddQuerryParameter2URL(returnurl, "e", timestr);
        return returnurl;
    }
	
    public static String CreateSMTicket(String url, String clientip, long second, String privatekey) 
	throws URISyntaxException, NoSuchAlgorithmException, UnsupportedEncodingException 
	{
        LocalDateTime now = LocalDateTime.ofInstant(Instant.now(), ZoneId.systemDefault()).plusSeconds(second);

        LocalDateTime epoch = LocalDateTime.ofInstant(Instant.EPOCH, ZoneId.systemDefault());
        long seconds = Duration.between(epoch, now).getSeconds();
        String timestr = String.valueOf(seconds);

        URI uri = new URI(url);
        String relativeurl = uri.getPath().substring(0, uri.getPath().lastIndexOf('/'));

        String key = privatekey + relativeurl + timestr + clientip;
        MessageDigest md5Hasher = MessageDigest.getInstance("MD5");
        byte[] hashedDataBytes = md5Hasher.digest(key.getBytes("ASCII"));

        String hash = Base64.getEncoder().encodeToString(hashedDataBytes);
        hash = hash.replace('+', '-').replace('/', '_').replace("=", "");

        String returnurl = AddQuerryParameter2URL(url, "st", hash);

        returnurl = AddQuerryParameter2URL(returnurl, "e", timestr);
        return returnurl;
    }

    public static String AddQuerryParameter2URL(String url, String parameter, String value) 
	throws UnsupportedEncodingException 
	{
        String seperator = "?";
        if (url.contains("?")) {
            seperator = "&";
        }

        return url + seperator + URLEncoder.encode(parameter, "UTF-8") + "=" + URLEncoder.encode(value, "UTF-8");
    }

}
