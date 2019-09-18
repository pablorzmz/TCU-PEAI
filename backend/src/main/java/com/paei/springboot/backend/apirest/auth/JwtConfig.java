package com.paei.springboot.backend.apirest.auth;

public class JwtConfig {

    public static final String RSA_PRIVATE = "-----BEGIN RSA PRIVATE KEY-----\n" +
            "MIIEowIBAAKCAQEAl5l8ca1+fz21wYfI0ON8F5AtsgtkgvgMRV0t3h8CCNHQq3jj\n" +
            "ew4e6n36qo4q4d9E/VXUb0YE7VoPqWQAakUhkWkAKHIWFyLdE66c/mcwkdQP8ANs\n" +
            "nscYRCVdGIOBzkdNUNlwGoCDlzuAgZXT/RHIPfE0uv2kCG22ytxffVIRERw3pb4Z\n" +
            "I2d6939MWmhihh82jpulWyxk8h8UEeO9Opoykz4/CUhdRSpgHQO5v9ZFg3oPRg3f\n" +
            "9822BsLn2HRiue/9ElwYEPB9ZqZfPrQZX2NO17p6+ufcVEbG5EQwqOlX34D8zcX0\n" +
            "stQokhoMSr5tXiTh8IZgr0bbZkHulWvaJ0aQfQIDAQABAoIBAHiNSkNPlp7yYJ10\n" +
            "L8HElEJcmanSm2v2ejOShE301HZU+p3QtPx+3yDTa0jDYuBY3pCh9xtBT9u62Wd3\n" +
            "KUlYjfgGymRfTKe7RphHK3q2bg4Y10+iOXcNlYNjPuOmd8ukMZxQPHAmr/3MCEWD\n" +
            "8p50zIpNTARCfHVjYY9xrNGIOCAk2dn4YThQkyF9N6jFZCiHxNOy+7VqSMfoUyKw\n" +
            "pHQ1+jLTxFHRW/uwZ9xVHLHC+xa+3/pX1eE0fCUL463Kg+b8ePd49WwnU6deCXJ+\n" +
            "NCQ9ZU7VVc30ah8hUnjZ/+6YNnUau5aT/us0/ZOlvUO3vzAk7MQIvyXpfYId3pbx\n" +
            "5y/egwECgYEAxywNoaCDvVg62OZ9LL+2nnDjTofSiNvx/tlWNbeJ6J4A2YeNn2u+\n" +
            "YBu43RSoUf6IutXkQvqxarakLWY59kQ5bC8Gzzwoo28TvtYIMGepC4UxtsAIr5nl\n" +
            "who5J5lDnCKSkCGD74y/Hrqla9tZdIDKaspfQwRgjYk1x8/FW8K0z70CgYEAwtqi\n" +
            "uLZste8obROp6Y/7SnEe3WiQf08HkC2u5veBakgCw3v+WrIvpVdz6cjvMs4AkaIn\n" +
            "skXsJxy89PtRO4qh5ohhA+/UG7z7PO3VYXeOM+CVHNmLhjYOd0wHHJHLuCBAHboo\n" +
            "Fgp2FI1Roy8I2BI+HNGMFyfZNMTWldBF53sfb8ECgYA0hLA/ezThu58gzH4bS54a\n" +
            "30eA0J26Sszf1209Et2BghBVS8ITVHTJoOW5HhZYMJbbNUVQSAx6SBi4g+kqv8io\n" +
            "dyoDgShyF/sL6iIUqXDQwdlfB/oCW8FS0llZ0LccN52P9dZ1VxaaQrsV2qwW5VL6\n" +
            "n8xIvqG7EIAm5Ol0jb3hvQKBgQCTvy5VOtXRWgdgNeHHXMphFscdgTTXo3gLpegR\n" +
            "76gsi2+1BGbIJIYmK9qTcS2Wa0dZJghNj+FRguPwKkPdDvBWGiDd2FMPuUsTVw9N\n" +
            "+VZVFbxbBMVbvc9YO8jEq/Q5qBKVt9q/Y61HRiSZxIdDNwnr1iQQRsHW1k4TiLoI\n" +
            "nH4owQKBgClXk6TtiEOUdyaQarX9/ai8eP2WbcRau+g3lIVokrwXiQwZT1VA5APr\n" +
            "j44Xi+2/AOJ/iq7t5TiVdroSoik4nRdnvv0OmOkyFoO4S9I6Q9BBbL+PhHrEfrZ3\n" +
            "D7MKfxOrKX6aJUk95A/4cBkaYWKA2OSq8SmjAhFk9nvijnNdzNy+\n" +
            "-----END RSA PRIVATE KEY-----";

    public static final String RSA_PUBLIC = "-----BEGIN RSA PRIVATE KEY-----\n" +
            "MIIEowIBAAKCAQEAl5l8ca1+fz21wYfI0ON8F5AtsgtkgvgMRV0t3h8CCNHQq3jj\n" +
            "ew4e6n36qo4q4d9E/VXUb0YE7VoPqWQAakUhkWkAKHIWFyLdE66c/mcwkdQP8ANs\n" +
            "nscYRCVdGIOBzkdNUNlwGoCDlzuAgZXT/RHIPfE0uv2kCG22ytxffVIRERw3pb4Z\n" +
            "I2d6939MWmhihh82jpulWyxk8h8UEeO9Opoykz4/CUhdRSpgHQO5v9ZFg3oPRg3f\n" +
            "9822BsLn2HRiue/9ElwYEPB9ZqZfPrQZX2NO17p6+ufcVEbG5EQwqOlX34D8zcX0\n" +
            "stQokhoMSr5tXiTh8IZgr0bbZkHulWvaJ0aQfQIDAQABAoIBAHiNSkNPlp7yYJ10\n" +
            "L8HElEJcmanSm2v2ejOShE301HZU+p3QtPx+3yDTa0jDYuBY3pCh9xtBT9u62Wd3\n" +
            "KUlYjfgGymRfTKe7RphHK3q2bg4Y10+iOXcNlYNjPuOmd8ukMZxQPHAmr/3MCEWD\n" +
            "8p50zIpNTARCfHVjYY9xrNGIOCAk2dn4YThQkyF9N6jFZCiHxNOy+7VqSMfoUyKw\n" +
            "pHQ1+jLTxFHRW/uwZ9xVHLHC+xa+3/pX1eE0fCUL463Kg+b8ePd49WwnU6deCXJ+\n" +
            "NCQ9ZU7VVc30ah8hUnjZ/+6YNnUau5aT/us0/ZOlvUO3vzAk7MQIvyXpfYId3pbx\n" +
            "5y/egwECgYEAxywNoaCDvVg62OZ9LL+2nnDjTofSiNvx/tlWNbeJ6J4A2YeNn2u+\n" +
            "YBu43RSoUf6IutXkQvqxarakLWY59kQ5bC8Gzzwoo28TvtYIMGepC4UxtsAIr5nl\n" +
            "who5J5lDnCKSkCGD74y/Hrqla9tZdIDKaspfQwRgjYk1x8/FW8K0z70CgYEAwtqi\n" +
            "uLZste8obROp6Y/7SnEe3WiQf08HkC2u5veBakgCw3v+WrIvpVdz6cjvMs4AkaIn\n" +
            "skXsJxy89PtRO4qh5ohhA+/UG7z7PO3VYXeOM+CVHNmLhjYOd0wHHJHLuCBAHboo\n" +
            "Fgp2FI1Roy8I2BI+HNGMFyfZNMTWldBF53sfb8ECgYA0hLA/ezThu58gzH4bS54a\n" +
            "30eA0J26Sszf1209Et2BghBVS8ITVHTJoOW5HhZYMJbbNUVQSAx6SBi4g+kqv8io\n" +
            "dyoDgShyF/sL6iIUqXDQwdlfB/oCW8FS0llZ0LccN52P9dZ1VxaaQrsV2qwW5VL6\n" +
            "n8xIvqG7EIAm5Ol0jb3hvQKBgQCTvy5VOtXRWgdgNeHHXMphFscdgTTXo3gLpegR\n" +
            "76gsi2+1BGbIJIYmK9qTcS2Wa0dZJghNj+FRguPwKkPdDvBWGiDd2FMPuUsTVw9N\n" +
            "+VZVFbxbBMVbvc9YO8jEq/Q5qBKVt9q/Y61HRiSZxIdDNwnr1iQQRsHW1k4TiLoI\n" +
            "nH4owQKBgClXk6TtiEOUdyaQarX9/ai8eP2WbcRau+g3lIVokrwXiQwZT1VA5APr\n" +
            "j44Xi+2/AOJ/iq7t5TiVdroSoik4nRdnvv0OmOkyFoO4S9I6Q9BBbL+PhHrEfrZ3\n" +
            "D7MKfxOrKX6aJUk95A/4cBkaYWKA2OSq8SmjAhFk9nvijnNdzNy+\n" +
            "-----END RSA PRIVATE KEY-----";
}
