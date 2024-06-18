## 一、项目介绍

### 1.前后台介绍

- 前台系统：是给用户看的ui界面的系统
  - 前端：vue
  - 后端：springboot微服务
- 后台系统：是给管理员或者商家使用的系统
  - 前端：vue
  - 后端：springboot微服务

### 2.前台项目部署

- 需要安装nodejs

```url
https://nodejs.org/en/download
```

下载完后直接安装即可。

前台项目需要的后端接口都是来自于后台项目提供的7777端口中的接口。之后我们需要替换成自己的后端项目提供的接口

- 安装依赖

```shell
npm install
```

- 运行

```shell
npm run dev
```

### 3.后台项目部署

- 将xmall.sql文件导入到数据库中

- 修改项目中的mysql服务器数据库的地址

![image-20210529092545720](img/image-20210529092545720.png)

- 启动一个redis服务器
- 在项目中部署redis服务器的信息

![image-20210529092620414](img/image-20210529092620414.png)

- 将项目配置在tomcat上

注意:把端口改成7777

<img src="img/image-20210529092657690.png" alt="image-20210529092657690" style="zoom:50%;" />

注意：项目部署在tomcat的根路径

![image-20210529092727720](img/image-20210529092727720.png)

## 二、单点登录

### 1.什么是单点登录

所谓的单点登录，是让分布式项目中各系统可以通过单点登陆模块（独立的）来完成登陆、登录验证、注销等功能，而不需要在各自的模块内都做一次登陆。

![截屏2023-11-06 19.46.16](img/截屏2023-11-06 19.46.16.png)

单点登录需要实现的功能模块：

- 登陆
- 验证是否已登陆
- 注销

### 2.登录接口

登录接口的关键是校验后的业务处理动作。下面我们来看下登录业务的流程图和时序图。

- 登录业务流程图

![登陆业务的流程图](img/登陆业务的流程图.png)

- 登录业务时序图

![截屏2023-11-06 20.11.56](img/截屏2023-11-06 20.11.56.png)



### 3.验证是否已登陆

在用户完成登录后，登录其他服务则通过判断，若已登陆则可以直接访问目标服务。

![验证是否已登陆的流程图](img/验证是否已登陆的流程图.png)

### 4.注销接口

实现注销功能。

![注销的业务流程](img/注销的业务流程.png)

## 三、JWT(Json Web Token)

### 1.基于Token的认证方式

> 使用基于Token的身份验证方法，在服务端不需要存储用户的登陆信息。流程如下：
>
> - 客户端使用用户名和密码请求登陆。
> - 服务端收到请求，去验证用户名和密码。
> - 验证成功后，服务端会签发一个Token，再把这个Token发送给客户端。
> - 客户端收到Token以后可以把它存储在Cookie本地。
> - 客户端每次向服务端请求资源时需要携带Cookie中该Token。
> - 服务端收到请求后，验证客户端携带的Token，如果验证成功则返回数据。

<img src="/Users/zeleishi/Documents/精品视频/2023/spring/资料/06-电商项目/img/jwt认证.png" alt="jwt认证" style="zoom:70%;" />

### 2.什么是JWT

> JSON Web Token （JWT）是一个开放的行业标准（RFC 7519），它定义了一种简洁的、自包含的协议格式，用于在通信双方传递json对象，传递的信息经过数字签名可以被验证和信任。JWT可以使用HMAC算法或使用RSA的公钥/私钥对进行签名，防止被篡改。
>
> JWT官网： https://jwt.io
>
> JWT令牌的优点：
>
> - JWT基于json，非常方便解析。
> - 可以在令牌中自定义丰富的内容，易扩展。
> - 通过非对称加密算法及数字签名技术，JWT防止篡改，安全性高。
> - 资源服务使用JWT可不依赖认证服务即完成授权。
>
> JWT令牌的缺点：
>
> - JWT令牌较长，占存储空间比较大。

### 3.JWT组成

> 一个JWT实际上就一个字符串，它由三部分组成，头部、负载与签名。

#### 1）头部（Header）

> 头部用于描述关于该JWT的最基本信息，例如其类型（即JWT）以及签名所用的算法（如HMAC SHA256 或 RSA）等。这也可以被表示成一个JSON对象。

```json
{
  "alg":"HS256",
  "typ":"JWT"
}
```

> - alg：签名算法
> - typ：类型
>
> 我们对头部的json字符串进行BASE64编码，编码后的字符串如下：

```
eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9
```

> Base64是一种基于64个可打印字符串来表示二进制数据的表示方式。JDK提供了非常方便的Base64Encoder和Base64Decoder，用它们可以非常方便的完成基于Base64的编码和解码。

#### 2）负载（Payload）

> 负载，是存放有效信息的地方，比如用户的基本信息可以存在该部分中。负载包含三个部分：
>
> - 标准中注册的声明（建议但不强制使用）
>   - iss：jwt签发者
>   - sub：jwt所面向的用户
>   - aud：接收jwt的一方
>   - exp：jwt的过期时间，过期时间必须大于签发时间
>   - nbf：定义在什么时间之前，该jwt都是不可用的
>   - iat：jwt的签发时间
>   - jti：jwt的唯一身份标识，主要用来作为一次性token，从而回避重放攻击。
>
> - 公共的声明
>
> 公共的声明可以添加任何信息，一般添加用户的相关信息或其他业务需要的必要信息，但不建议添加敏感信息，因为该部分在客户端可解密。
>
> - 私有的声明
>
> 私有声明是提供者和消费者所共同定义的声明，一般不建议存放敏感信息，因为base64是对称解密的，意味着该部分信息可以归类为明文信息。
>
> 私有声明也就是自定义claim，用于存放自定义键值对。

```json
{
  "sub": "1234567890",
  "name": "John Doe",
  "iat": 1516239022
}
```

> 其中sub是标准的声明，name是自定义的私有声明，编码后如下：

```
eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ
```

#### 3）签证、签名（Signature）

> jwt的第三部分是一个签证信息，由三部分组成：
>
> - Header（Base64编码后）
> - Payload（Base64编码后）
> - Secret（盐，必须保密）
>
> 这个部分需要Base64加密后的header和base4加密后的payload使用.连接组成的字符串，然后通过header重声明的加密方式进行加盐Secret组合加密，然后就构成了JWT的第三部分——使用“qfjava”作为盐：

```
eZqdTo1mRMB-o7co1oAiTvNvumfCkt-1H-CdfNm78Cw
```

> 从官方工具中可以看到，三个部分组合出的完整字符串：

```
eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.eZqdTo1mRMB-o7co1oAiTvNvumfCkt-1H-CdfNm78Cw
```

![image-20210310163620788](/Users/zeleishi/Documents/精品视频/2023/spring/资料/06-电商项目/img/jwttoken.png)



> 注意：secret是保存在服务器端的，jwt在签发生成也是在服务器端的，secret就是用来进行jwt的签发和验证，所以，它就是服务器端的私钥，在任何场景都不应该泄漏。一旦客户端得知这个secret，那就意味着客户端是可以自我签发jwt了。

### 4.使用JJWT

JJWT是一个提供端到端的JWT创建和验证的开源Java库。也就是说使用JJWT能快速完成JWT的功能开发。

- 引入依赖

> 创建Springboot工程并引入jjwt依赖，pom.xml如下：

```xml
        <!--jjwt-->
        <dependency>
            <groupId>io.jsonwebtoken</groupId>
            <artifactId>jjwt</artifactId>
            <version>0.9.1</version>
        </dependency>
```

- 创建Token

```java
    @Test
    public void testCrtToken(){

        //创建JWT对象
        JwtBuilder builder = Jwts.builder().setId("1001")//设置负载内容
                .setSubject("小明")
                .setIssuedAt(new Date())//设置签发时间
                .signWith(SignatureAlgorithm.HS256, "qfjava");//设置签名秘钥
        //构建token
        String token = builder.compact();
        System.out.println(token);

    }
```



> JWT将用户信息转换成Token字符串，生成结果如下：

```
eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMDAxIiwic3ViIjoi5bCP5piOIiwiaWF0IjoxNjE1MzY2MDEyfQ.2LNcw1v64TNQ96eCpWKvtAccBUA-cEVMDyJNMef-zu0
```



- 解析Token

> 通过JWT解析Token，获取Token中存放的用户信息，即生成Claims对象。

```java
    @Test
    public void testParseToken(){
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMDAxIiwic3ViIjoi5bCP5piOIiwiaWF0IjoxNjE1MzY2MDEyfQ.2LNcw1v64TNQ96eCpWKvtAccBUA-cEVMDyJNMef-zu0";
        //解析Token，生成Claims对象，Token中存放的用户信息解析到了claims对象中
        Claims claims = Jwts.parser().setSigningKey("qfjava").parseClaimsJws(token).getBody();
        System.out.println("id:" + claims.getId());
        System.out.println("subject:" + claims.getSubject());
        System.out.println("IssuedAt:" + claims.getIssuedAt());
    }
```

> 解析结果如下：

```
id:1001
subject:小明
IssuedAt:Wed Mar 10 16:46:52 CST 2021
```



- Token过期检验

> 在有效期内Token可以正常读取，超过有效期则Token失效

```java
    @Test
    public void testExpToken(){
        long now = System.currentTimeMillis();  //当前时间
        long exp = now + 1000 * 60; //过期时间为1分钟
        JwtBuilder builder = Jwts.builder().setId("1001")
                .setSubject("小明")
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256, "qfjava")
                .setExpiration(new Date(exp));//设置超时
    }
```



- 自定义claims

> 除了使用官方api设置属性值，也可以添加自定义键值对。

```java
    @Test
    public void testCustomClaims(){
        long now = System.currentTimeMillis();  //当前时间
        long exp = now + 1000 * 60; //过期时间为1分钟
        JwtBuilder builder = Jwts.builder().setId("1001")
                .setSubject("小明")
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256, "qfjava")
                .setExpiration(new Date(exp))
                .claim("role", "admin");//设置自定义键值对
    }
```

> 使用下面语句获取属性值：

```java
claims.get("role")
```

