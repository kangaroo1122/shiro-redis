package com.kangaroohy.shiroredis.config.serializer;

import com.kangaroohy.shiroredis.exception.CustomException;
import lombok.extern.slf4j.Slf4j;

import java.io.*;

/**
 * @author kangaroo hy
 * @version 0.0.1
 * @desc TODO
 * @since 2020/3/7
 */
@Slf4j
public class RedisSerializer {

    private RedisSerializer() {
    }

    /**
     * 序列化
     *
     * @param object
     * @return byte[]
     */
    public static byte[] serializable(Object object) {
        ByteArrayOutputStream baos = null;
        ObjectOutputStream oos = null;
        try {
            baos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(baos);
            oos.writeObject(object);
            return baos.toByteArray();
        } catch (IOException e) {
            log.error("SerializableUtil工具类序列化出现IOException异常:{}", e.getMessage());
            throw new CustomException("SerializableUtil工具类序列化出现IOException异常:" + e.getMessage());
        } finally {
            try {
                if (oos != null) {
                    oos.close();
                }
                if (baos != null) {
                    baos.close();
                }
            } catch (IOException e) {
                log.error("SerializableUtil工具类反序列化出现IOException异常:{}", e.getMessage());
                throw new CustomException("SerializableUtil工具类反序列化出现IOException异常:" + e.getMessage());
            }
        }
    }

    /**
     * 反序列化
     *
     * @param bytes
     * @return java.lang.Object
     */
    public static Object unserializable(byte[] bytes) {
        ByteArrayInputStream bais = null;
        ObjectInputStream ois = null;
        try {
            bais = new ByteArrayInputStream(bytes);
            ois = new ObjectInputStream(bais);
            return ois.readObject();
        } catch (ClassNotFoundException e) {
            log.error("SerializableUtil工具类反序列化出现ClassNotFoundException异常:{}", e.getMessage());
            throw new CustomException("SerializableUtil工具类反序列化出现ClassNotFoundException异常:" + e.getMessage());
        } catch (IOException e) {
            log.error("SerializableUtil工具类反序列化出现IOException异常:{}", e.getMessage());
            throw new CustomException("SerializableUtil工具类反序列化出现IOException异常:" + e.getMessage());
        } finally {
            try {
                if (ois != null) {
                    ois.close();
                }
                if (bais != null) {
                    bais.close();
                }
            } catch (IOException e) {
                log.error("SerializableUtil工具类反序列化出现IOException异常:{}", e.getMessage());
                throw new CustomException("SerializableUtil工具类反序列化出现IOException异常:" + e.getMessage());
            }
        }
    }
}
