package com.org.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;



public class RSASignature {

	/**

	* 瑙ｅ瘑

	* @param content 瀵嗘枃
	* 
	* @param key 鍟嗘埛绉侀挜

	* @return 瑙ｅ瘑鍚庣殑瀛楃锟�

	*/
	public static String decrypt(String content, String key) throws Exception {
        PrivateKey prikey = getPrivateKey(key);

        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, prikey);

        InputStream ins = new ByteArrayInputStream(Base64.decode(content));
        ByteArrayOutputStream writer = new ByteArrayOutputStream();
        //rsa瑙ｅ瘑鐨勫瓧鑺傚ぇ灏忔渶澶氭槸128锛屽皢锟�锟斤拷瑙ｅ瘑鐨勫唴瀹癸紝锟�28浣嶆媶锟�锟斤拷锟�
        byte[] buf = new byte[128];
        int bufl;

        while ((bufl = ins.read(buf)) != -1) {
            byte[] block = null;

            if (buf.length == bufl) {
                block = buf;
            } else {
                block = new byte[bufl];
                for (int i = 0; i < bufl; i++) {
                    block[i] = buf[i];
                }
            }

            writer.write(cipher.doFinal(block));
        }

        return new String(writer.toByteArray(), "utf-8");
    }


	
	/**

	* 寰楀埌绉侀挜

	* @param key 瀵嗛挜瀛楃涓诧紙缁忚繃base64缂栫爜锟�

	* @throws Exception

	*/

	public static PrivateKey getPrivateKey(String key) throws Exception {

		byte[] keyBytes;
		
		keyBytes = Base64.decode(key);
		
		PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
		
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		
		PrivateKey privateKey = keyFactory.generatePrivate(keySpec);
		
		return privateKey;

	}
	
	public static final String  SIGN_ALGORITHMS = "SHA1WithRSA";
	/**
	* RSA绛惧悕
	* @param content 寰呯鍚嶆暟锟�
	* @param privateKey 鍟嗘埛绉侀挜
	* @param encode 瀛楃闆嗙紪锟�
	* @return 绛惧悕锟�
	*/
	public static String sign(String content, String privateKey,String encode)
	{
		String charset = "utf-8";
		if(!StringUtil.isBlank(encode)){
		    charset=encode;
		}
        try 
        {
        	PKCS8EncodedKeySpec priPKCS8 	= new PKCS8EncodedKeySpec( Base64.decode(privateKey) ); 
        	KeyFactory keyf 				= KeyFactory.getInstance("RSA");
        	PrivateKey priKey 				= keyf.generatePrivate(priPKCS8);

            java.security.Signature signature = java.security.Signature
                .getInstance(SIGN_ALGORITHMS);

            signature.initSign(priKey);
            signature.update( content.getBytes(charset) );

            byte[] signed = signature.sign();
            
            return Base64.encode(signed);
        }
        catch (Exception e) 
        {
        	e.printStackTrace();
        }
        
        return null;
    }
	
	/**
	* RSA楠岀鍚嶆锟�
	* @param content 寰呯鍚嶆暟锟�
	* @param sign 绛惧悕锟�
	* @param publicKey 鏀粯瀹濆叕锟�
	* @param encode 瀛楃闆嗙紪锟�
	* @return 甯冨皵锟�
	*/
	public static boolean doCheck(String content, String sign, String publicKey,String encode)
	{
	    String charset = "utf-8";
        if(!StringUtil.isBlank(encode)){
            charset=encode;
        }
		try 
		{
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
	        byte[] encodedKey = Base64.decode(publicKey);
	        PublicKey pubKey = keyFactory.generatePublic(new X509EncodedKeySpec(encodedKey));

		
			java.security.Signature signature = java.security.Signature
			.getInstance(SIGN_ALGORITHMS);
		
			signature.initVerify(pubKey);
			signature.update( content.getBytes(charset) );
		
			boolean bverify = signature.verify( Base64.decode(sign) );
			return bverify;
			
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		return false;
	}

}
