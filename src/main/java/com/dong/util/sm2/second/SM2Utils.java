package com.dong.util.sm2.second;

import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.crypto.params.ECPrivateKeyParameters;
import org.bouncycastle.crypto.params.ECPublicKeyParameters;
import org.bouncycastle.math.ec.ECPoint;

import java.io.IOException;
import java.math.BigInteger;

/**
 * @author LD
 */
public class SM2Utils {

    //生成随机秘钥对
    public static SM2KeyPair generateKeyPair() {
        SM2 sm2 = SM2.Instance();
        AsymmetricCipherKeyPair key = sm2.ecc_key_pair_generator.generateKeyPair();
        ECPrivateKeyParameters ecpriv = (ECPrivateKeyParameters) key.getPrivate();
        ECPublicKeyParameters ecpub = (ECPublicKeyParameters) key.getPublic();
        BigInteger privateKey = ecpriv.getD();
        ECPoint publicKey = ecpub.getQ();
//        System.out.println("公钥: " + Util.byteToHex(publicKey.getEncoded()));
//        System.out.println("私钥: " + Util.byteToHex(privateKey.toByteArray()));
        return new SM2KeyPair(Util.byteToHex(publicKey.getEncoded(false)), Util.byteToHex(privateKey.toByteArray()));
    }


    //数据加密
    public static String encrypt(byte[] publicKey, byte[] data) throws IOException {
        if (publicKey == null || publicKey.length == 0) {
            return null;
        }

        if (data == null || data.length == 0) {
            return null;
        }

        byte[] source = new byte[data.length];
        System.arraycopy(data, 0, source, 0, data.length);

        Cipher cipher = new Cipher();
        SM2 sm2 = SM2.Instance();
        ECPoint userKey = sm2.ecc_curve.decodePoint(publicKey);

        ECPoint c1 = cipher.Init_enc(sm2, userKey);
        cipher.Encrypt(source);
        byte[] c3 = new byte[32];
        cipher.Dofinal(c3);

//      System.out.println("C1 " + Util.byteToHex(c1.getEncoded()));
//      System.out.println("C2 " + Util.byteToHex(source));
//      System.out.println("C3 " + Util.byteToHex(c3));
        //C1 C2 C3拼装成加密字串
        return Util.byteToHex(c1.getEncoded(false)) + Util.byteToHex(source) + Util.byteToHex(c3);

    }

    //数据解密
    public static byte[] decrypt(byte[] privateKey, byte[] encryptedData) throws IOException {
        if (privateKey == null || privateKey.length == 0) {
            return null;
        }

        if (encryptedData == null || encryptedData.length == 0) {
            return null;
        }
        //加密字节数组转换为十六进制的字符串 长度变为encryptedData.length * 2
        String data = Util.byteToHex(encryptedData);
        /***分解加密字串
         * （C1 = C1标志位2位 + C1实体部分128位 = 130）
         * （C3 = C3实体部分64位  = 64）
         * （C2 = encryptedData.length * 2 - C1长度  - C2长度）
         */
        byte[] c1Bytes = Util.hexToByte(data.substring(0, 130));
        int c2Len = encryptedData.length - 97;
        byte[] c2 = Util.hexToByte(data.substring(130, 130 + 2 * c2Len));
        byte[] c3 = Util.hexToByte(data.substring(130 + 2 * c2Len, 194 + 2 * c2Len));

        SM2 sm2 = SM2.Instance();
        BigInteger userD = new BigInteger(1, privateKey);

        //通过C1实体字节来生成ECPoint
        ECPoint c1 = sm2.ecc_curve.decodePoint(c1Bytes);
        Cipher cipher = new Cipher();
        cipher.Init_dec(userD, c1);
        cipher.Decrypt(c2);
        cipher.Dofinal(c3);

        //返回解密结果
        return c2;
    }

    public static void main(String[] args) throws IOException {
        String privateKey = "5854da5c8d44c39568b2ce10fea45e93e8329fd6249a1d959a2478c467f11a2d";
        String publicKey = "043d1e9be1b7202c085b9f0a0fff002e0d5d878716ec32e36a0c2675b4c2ca4bd1b320ad0f5167ce476df387f097f404aa2760d4a7f8c523fd6ceae96d7f12ca37";
        String content = "043d91d4c8a970ef7681c7c4d3e2a242cc32f7eb1825715ee31dc8426bf2cbd930d6b899de0746bbe18071773b59a0616e703571caa809c173a3abb0803aa3a519d6a55bbf2532d331f8f1d935d2d886a0426d65b8f13604571585fe8bd03973ba260132e8f97ce2bcc6f0909fbcf7b16e25d1b858ef59b77e480bc7ead7aabfc4d84218ba243b1fa4214bde5b68f92b2101983c0d430b60fea7af111af2e457af8d5de74dc344302dddd163f7af5bd3b1a36ba98bbe3b5ee21ed937d05264076f00a8a868cb71ee699f82f9c3f0bc53448f76ded2f47a578dc61792a0c95b3696e69c29cf5be97b619d5b8bd980a8458831efbc1ea32c7144bb14c2cdacc25d58d4126cee8059b0ac71ecbf23ac699e35acba8ccb84aee170226f1b0b8947af06295ad451ab455029189627571b0ef281620749c42c30929f1fae157a84ae2af26fce744ea82e94776cc23bca23b1e9af846f88de9096a74fba0d4868795a48b35270b08cd09d7cf73433fad5c5d79ce87e37630bc1d2fe6a759e40c014c139be24ca250fb39ea7fb55c52883eed3d62a2e50d74dc9f931560622d10375e94247bf19bc76e07852984f02224d5edb604d454080a5b5706760dd2e38765c01ef20831e8d10746251514f757073f7580ef6193e74e5422d93dbcd262c4d3da4ebd5e5e89c280c71c73b389d935a6c596395a246c7567a4dba1f92bd7011db88ba018f3fbf158a8b67ed30d8ca529c59edd20fdfd25d0c7d67b98c2cb07c93bde7585c9279dd6b32c60e83d0d01c1a1009b9ac2e7693e05d6baa5def34f8cba4818f2a4afe291976bd64da501e049bbfe380b6bcd50b328f78466e7ad81e74bc8e88ec1f4b12e8d71f65d5616ff1536f57613810327af26344ba983cd4cb09445764959321918883bf300e002702a09b82c6940b1d37cbb0a602fe26e2c04b8bdebceda2607330f35b99f06dcd17af8e23327fd4e0527e6ec6d9bc9ef485aa7fc4ef7653251cb00f427bfd8e8477780ab1eee09edc96bdc773d8e1a458ed1b116666ddc876e9b88d8f01c474c27896e81139b60ccd6b157b2b167a9443e070affe4c5a44ba9e1c7ca0c95feeb6c6614990dadb2f4731263174c68941f28dd8c3170fa414b6285ad98b42aa499b4430a2a7086e96f1cb38926e49852b46155c784aeacd417c29f34a4356923c43f98f6b5721cec6502f320e50134284dcb12be5811d07cb029275fb5b483330c2aafa35641384c3b9238ad16b99e7bc2d4fa1ee0105953a0a4aae8ca80e5f129e8a73cf586534dfb7b18f4fa1bf5b5a7b555d6778ba1eb75c8eeeaa124888c158721c17b5f14babc7b791a3d00e4d9c227e5590e95c0baa3fe13781830dba9928305b5276bba258e25d62d9739f5dab1ead8844d0ec9078fe9c64db038e16f002089345cb3f754dc6689f6c3377a55f8165ec652dc33e875e0fb5bf17f26e303cd5053990542d4bd4de8138f8717837e3e8692a4d07337abebe9cb2c8a962887bb646a62843b79553498498e90a1005125cb22ec0aaac37b0ce4ab9a1726b23030b02be5481882b19c1cd7a60d08553b234cdaf147eaa8b2acc622034e404af034dfc6110ba333946c841011cd1a2ad5cd1fa5e667433b5bcc195683aea8d1aae5a0cfd701e44497832aaeeb60dac41ecc399df6db127f80ae36c0af1c99b3c15f1b16c267618c1be60d569ee3211064c003b9caff242b05f3a95d80069e57e64dd3f6eee593c83b31ba90d53ef3f2d3685fd6091d632edf92a9ce198480416edb6cd429f0150553ca5a587bb760b49c77ed5c9e9deb3b74cf5e69f986d51cb808412c73af918ad580a91e808804286a1d8f2c282f5a221787b328d68ceda9a4fcf100848432aad36f552db469eb25db99757a2ab3ede4d046f3cda4a5290ec4f5d3ab88b24a336252d11092c9151057a38c6044c46fedc533a4ea88398a20ecbdb13c83c2f2427b900fbf4f91c31764ceebc41cef93deaa036680054b71252fcf5b190b3bde995133ac88eab771ce3daa2926cea72dceff83d2975159d80ffabd8ee47294a4522b3e9f182eefd7786a4f35d6bab6219409381bc44187ee3e0978e78de638575d52e099f970afef8e53ddc0b584166338729ce4fac00a67905d7c20dd5b555409f6633fd70cba909f46aa5842645d20f9a993f11f5c09e901b04b754790a5321ccb2b5e4b32d4c8237a1c5c6f42ef994d7a5d2cf802f4675b05a78f8c51e446e4843d2962f3baa5fc3a5154f5f46a6723411d066a7a3d8cf0ce48f9529becf087ce786d4e9908f1d1d91edad9940fe0823d5772c5be81e0875ec70919a66b17d8132418fee0b4ab40525d16a5de3c6b8ae9e153fffc6bce17c9cde79ecbc8703ed76fcb2d1a422108c11f87327dc2afb7ced408faa05f0c723cf6eb1acdf52190ad9e39a8ea7913c4890db2ef498a7d3641f707ba1c7977555d18b6fa2c0be651a73dae97e63ba5d5d2fc63101c89deb4997847ab09e69b24435991c48690b42400ad040911c803f4871cb6c2268820455f0f7c6d8fa39015971b9d918f214d2f9e3494bc9a9feb11cc651b3baa9ca8e952dd7646c9f347a7ed243f963231ddada72ff7c4f1498483d0491767f7d39673d78436f67ec1e6a5da304f21fcf779bc8d7c06051fff3bf6e7411d2ffd22a5edd97f98c9daf55b33c74388fbbe664d01e032f4ee4643130cd40d14b37cc8ec4ef4346e033bb4132901a42f16504637d0c267173c3871a280e0476226d5bc9ae8d2f8c5d651fc0060f3a48f88624f8e30fbf1993f5257481fea458b51b643467736acceb8ff80e30cd341029c053050806d95d34fc324024e4265959f73796b969302889dda221c4e0d566f24ba7e750fab31321c1ab54fa19ffdde32145972041c7273b3f1dc9f6138ea83678cdde73036416511ec4fcd892a560b9c187dd4b73c88829318cbaa078e2edeee270768a78968deb7e3801a2925b4a4b2ec1f56d276da701ec889838b495263c0b41cbd650a3e316176fc9d21ea334047b8bb3e98c73b64d4c2be422d8328964c4847273fde400a61e458b53466217532ebfacdac89f3bba5420e0debbd65aa4459a95dd4a4b518545d0e14f466d9067a95ba4c7f2da778c05176039892298705dfe94a4ee731b8765c1d3170d2d2b379c093dc2103809417b2f23f4b60b5d3bed6f31249b78045f5271908af3421071caf1cf5d944970c62bb67c41ee4fba1f70d35bade3dc25b194dc3587d59820c88b56dc5476ae036477e20209a19db0b601382ba0d4278b6229e8c5bf6aa2bc5a0985919666fa965f13724bfcf189c9d5828acd1dbf2db7ba6e556e011f340eaff1c9d07b8b814743d04b7f97c254bb941ba4d5de1b8ba01e7a6bec72659fd0e8915651f945e7da0fe3d490e0a64b65dfd525020cd1c23e2447ebf6d6ef48e37b65a0e4a452fc7bba9b960813ae248b310d622057742964f9f7574e1316326ee06dd641f4149f9f24c3468522ae3450452ad8cd9c117d173ff44b0377d10f882ea732c60a5b86d5bd6719610147e1dceef88a8ae93dc8095e8c265e92e0b73521bc0da846f791bde4124d60bc79b3b8329ac8b8ab25a89f14f55c75210aacbb418c85788ab18f5d59c1b84b6f284cce412dca220a5598d5b8bbf9aea92ecb005aeeb94e5e956ccc981bbc18fcb2f86421651c6f7e8cf4499ebcb787eef162d6a97f02d04a8b542434920ecbafb2d9fa5ab08b709982c0e9742ea9bc452856c9d093ec2ef953fa9a546db154f1a4ebb58bb87a33273e6f3efb393b42c3953b21e1063fea72d000e70d1ae280d7c9ee53af86e3a1700f9b2b289e86305ec87be65e1b49dce1851c862ea635c8d039a770678d99efd706660499a4d12ebffff1363905c9ec7b0eca9a39d10cf65351ab4dca4e29ba1d436713009bc1b9879792433ec1966fe06282eeaa8712722feb8a9aea3075280473811ab2208d4c78188e7351b03e5df302a80e0a08c518d8b10db6b34237170455dbd69e376a6acf5e9e38ebfaaf229fe5869f29f93ee105cdcb7b7b762f9dcb18cc8e7cafa78078039168569f66576812d22e34f9f41d884c66728e78a4b41084c812d5a4c4345081bec82911916043391cc8e5afadc6732fdf8f3b7572227d18214c3de85a1890a2ef13256a9f1e54ba88fe90a9d248723b2825a97afe121883ee84748a";
        String data = "{\n" +
                "\t\"操作票状态\": \"执行中\",\n" +
                "\t\"审核人\": \"杜江\",\n" +
                "\t\"模块名称\": \"OMS系统与操作票系统数据交互\",\n" +
                "\t\"备注\": \"\",\n" +
                "\t\"设备名称\": \"\",\n" +
                "\t\"requestId\": \"b41708e3-9f78-4574-b2f6-0cd6cfdee8fa\",\n" +
                "\t\"操作票编号\": \"普2020050003\",\n" +
                "\t\"盖章类型\": \"\",\n" +
                "\t\"数据功能\": \"同步操作票数据\",\n" +
                "\t\"操作结束时间\": \"\",\n" +
                "\t\"needback\": true,\n" +
                "\t\"操作指令\": [\n" +
                "\t\t{\n" +
                "\t\t\t\"发令人\": \"陈龙\",\n" +
                "\t\t\t\"顺序\": \"1\",\n" +
                "\t\t\t\"受令人\": \"何怀玲\",\n" +
                "\t\t\t\"受令单位\": \"兴义监控\",\n" +
                "\t\t\t\"操作项目\": \"将银山变220kV银练线205开关由热备用转冷备用\",\n" +
                "\t\t\t\"合序\": \"1\",\n" +
                "\t\t\t\"操作人\": \"\",\n" +
                "\t\t\t\"指令序号\": \"1\",\n" +
                "\t\t\t\"指令状态\": \"厂站确认\",\n" +
                "\t\t\t\"指令ID\": \"a1430dc2b4844588ba0c2ed55f46892c\",\n" +
                "\t\t\t\"完成时间\": \"2020-05-21 11:42:56\",\n" +
                "\t\t\t\"回令人\": \"何怀玲\",\n" +
                "\t\t\t\"发令时间\": \"2020-05-21 11:32:58\"\n" +
                "\t\t},\n" +
                "\t\t{\n" +
                "\t\t\t\"发令人\": \"\",\n" +
                "\t\t\t\"顺序\": \"2\",\n" +
                "\t\t\t\"受令人\": \"\",\n" +
                "\t\t\t\"受令单位\": \"六盘水监控\",\n" +
                "\t\t\t\"操作项目\": \"将练池塘变220kV银练线205开关由热备用转冷备用\",\n" +
                "\t\t\t\"合序\": \"2\",\n" +
                "\t\t\t\"操作人\": \"\",\n" +
                "\t\t\t\"指令序号\": \"2\",\n" +
                "\t\t\t\"指令状态\": \"待下令\",\n" +
                "\t\t\t\"指令ID\": \"4c30255b84354fde972962b992716a87\",\n" +
                "\t\t\t\"完成时间\": \"\",\n" +
                "\t\t\t\"回令人\": \"\",\n" +
                "\t\t\t\"发令时间\": \"\"\n" +
                "\t\t},\n" +
                "\t\t{\n" +
                "\t\t\t\"发令人\": \"\",\n" +
                "\t\t\t\"顺序\": \"3\",\n" +
                "\t\t\t\"受令人\": \"\",\n" +
                "\t\t\t\"受令单位\": \"兴义监控\",\n" +
                "\t\t\t\"操作项目\": \"合上银山变220kV银练线2059线路地刀\",\n" +
                "\t\t\t\"合序\": \"3\",\n" +
                "\t\t\t\"操作人\": \"\",\n" +
                "\t\t\t\"指令序号\": \"3\",\n" +
                "\t\t\t\"指令状态\": \"待下令\",\n" +
                "\t\t\t\"指令ID\": \"ea8601934a3341259d77d57afba3a612\",\n" +
                "\t\t\t\"完成时间\": \"\",\n" +
                "\t\t\t\"回令人\": \"\",\n" +
                "\t\t\t\"发令时间\": \"\"\n" +
                "\t\t},\n" +
                "\t\t{\n" +
                "\t\t\t\"发令人\": \"\",\n" +
                "\t\t\t\"顺序\": \"4\",\n" +
                "\t\t\t\"受令人\": \"\",\n" +
                "\t\t\t\"受令单位\": \"六盘水监控\",\n" +
                "\t\t\t\"操作项目\": \"在练池塘变220kV银练线2053刀闸靠线路处装设三相短路接地线一组\",\n" +
                "\t\t\t\"合序\": \"4\",\n" +
                "\t\t\t\"操作人\": \"\",\n" +
                "\t\t\t\"指令序号\": \"4\",\n" +
                "\t\t\t\"指令状态\": \"待下令\",\n" +
                "\t\t\t\"指令ID\": \"d18eba5349774beda2fae85cd9074616\",\n" +
                "\t\t\t\"完成时间\": \"\",\n" +
                "\t\t\t\"回令人\": \"\",\n" +
                "\t\t\t\"发令时间\": \"\"\n" +
                "\t\t},\n" +
                "\t\t{\n" +
                "\t\t\t\"发令人\": \"\",\n" +
                "\t\t\t\"顺序\": \"5\",\n" +
                "\t\t\t\"受令人\": \"\",\n" +
                "\t\t\t\"受令单位\": \"兴义监控\",\n" +
                "\t\t\t\"操作项目\": \"220kV银练线已由热备用转检修\",\n" +
                "\t\t\t\"合序\": \"5\",\n" +
                "\t\t\t\"操作人\": \"\",\n" +
                "\t\t\t\"指令序号\": \"5\",\n" +
                "\t\t\t\"指令状态\": \"待下令\",\n" +
                "\t\t\t\"指令ID\": \"9bf6cbb858e4411cac540f1baeed055a\",\n" +
                "\t\t\t\"完成时间\": \"\",\n" +
                "\t\t\t\"回令人\": \"\",\n" +
                "\t\t\t\"发令时间\": \"\"\n" +
                "\t\t},\n" +
                "\t\t{\n" +
                "\t\t\t\"发令人\": \"\",\n" +
                "\t\t\t\"顺序\": \"6\",\n" +
                "\t\t\t\"受令人\": \"\",\n" +
                "\t\t\t\"受令单位\": \"运检公司\",\n" +
                "\t\t\t\"操作项目\": \"220kV银练线已由热备用转检修\",\n" +
                "\t\t\t\"合序\": \"6\",\n" +
                "\t\t\t\"操作人\": \"\",\n" +
                "\t\t\t\"指令序号\": \"6\",\n" +
                "\t\t\t\"指令状态\": \"待下令\",\n" +
                "\t\t\t\"指令ID\": \"aec1f6218ea34e449a908128cd531f4f\",\n" +
                "\t\t\t\"完成时间\": \"\",\n" +
                "\t\t\t\"回令人\": \"\",\n" +
                "\t\t\t\"发令时间\": \"\"\n" +
                "\t\t}\n" +
                "\t],\n" +
                "\t\"操作票ID\": \"a4bc81a9237c46598d8b31ebb3d97904\",\n" +
                "\t\"操作票类型\": \"普通操作票\",\n" +
                "\t\"请求应答\": \"请求\",\n" +
                "\t\"操作人\": \"陈龙\",\n" +
                "\t\"操作任务\": \"220kV银练线由热备用转检修\",\n" +
                "\t\"拟票人\": \"陈龙\",\n" +
                "\t\"检修单编号\": \"茶园电厂20200400001,贵州黔源集控中心20200400002,六盘水供电局/变电管理所20200400013\",\n" +
                "\t\"操作模式\": \"直接操作\",\n" +
                "\t\"生成时间\": \"2020-05-21 11:43:58\",\n" +
                "\t\"接收者\": \"OMS系统\",\n" +
                "\t\"操作开始时间\": \"2020-05-21 11:32:59\",\n" +
                "\t\"发送者\": \"操作票系统\",\n" +
                "\t\"值班负责人\": \"肖倩宏\",\n" +
                "\t\"停复电类型\": \"停电\"\n" +
                "}";
        String decrypt = net.qianchi.utils.sm.SMSecurityUtils.sm2Decrypt(content,privateKey);
//        String data1 = net.qianchi.utils.sm.SMSecurityUtils.sm2Encrypt(content,publicKey);
        System.out.println(decrypt);
    }
}
