# spring-demo

这是一个面向 Spring Boot 初学者的最小可运行项目，当前包含一个简单的 HTTP 接口：

```text
GET /api/v1/hello
```

访问后会返回一段文本，用来验证 Spring Boot 应用是否启动成功、控制器是否被扫描，以及请求映射是否生效。

## 一、环境要求

- JDK 21（项目中的 `pom.xml` 使用 `<java.version>21</java.version>`）
- Maven 3.6.3 或更高版本
- 任意 Java IDE，例如 IntelliJ IDEA、Eclipse 或 VS Code

项目同时提供了 Maven Wrapper，因此通常可以直接使用项目根目录下的 `./mvnw`，不必单独安装 Maven。

检查环境：

```bash
java -version
mvn -version
```

如果使用 Maven Wrapper：

```bash
./mvnw -version
```

Windows 命令行使用：

```bat
mvnw.cmd -version
```

## 二、项目结构

```text
spring-demo/
├── pom.xml
├── mvnw
├── mvnw.cmd
├── README.md
└── src/
    ├── main/
    │   ├── java/com/study/spring_demo/
    │   │   └── SpringDemoApplication.java
    │   └── resources/
    │       └── application.yml
    └── test/
        └── java/com/study/spring_demo/
            └── SpringDemoApplicationTests.java
```

### 主要文件说明

- `pom.xml`：项目依赖和 Maven 构建配置。
- `SpringDemoApplication.java`：Spring Boot 启动类，同时包含本示例的控制器接口。
- `application.yml`：端口和项目上下文路径配置。
- `SpringDemoApplicationTests.java`：验证 Spring 应用上下文能否正常启动。

## 三、配置说明

当前 `src/main/resources/application.yml` 内容如下：

```yaml
server:
  servlet:
    context-path: /api/v1
  port: 9000
```

这代表：

- 应用端口是 `9000`，不是默认的 `8080`。
- 项目统一上下文路径是 `/api/v1`。
- 所以控制器中的 `/hello`，对外完整地址是 `/api/v1/hello`。

## 四、启动项目

在项目根目录执行：

### 使用 Maven Wrapper

macOS 或 Linux：

```bash
./mvnw spring-boot:run
```

Windows：

```bat
mvnw.cmd spring-boot:run
```

### 使用本机 Maven

```bash
mvn spring-boot:run
```

启动成功后，控制台通常会看到类似信息：

```text
Tomcat started on port 9000
Started SpringDemoApplication
```

## 五、访问接口

浏览器访问：

```text
http://localhost:9000/api/v1/hello
```

也可以使用 `curl`：

```bash
curl http://localhost:9000/api/v1/hello
```

预期返回：

```text
Hello, Spring Boot! 我的第一个接口成功啦！
```

## 六、代码说明

```java
@SpringBootApplication
@RestController
public class SpringDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringDemoApplication.class, args);
    }

    @GetMapping("/hello")
    public String sayHello() {
        return "Hello, Spring Boot! 我的第一个接口成功啦！";
    }
}
```

### `@SpringBootApplication`

这是 Spring Boot 的核心启动注解，通常包含三个作用：

- 标记当前类是 Spring Boot 启动类。
- 开启自动配置。
- 从当前包及其子包扫描 Spring 组件。

因此，启动类最好放在业务代码的较上层包中，例如本项目的 `com.study.spring_demo`。

### `@RestController`

这个注解告诉 Spring：当前类是一个 Web 控制器，并且方法返回值直接作为 HTTP 响应内容。

如果缺少它，Spring 不会把 `sayHello()` 当成接口处理，访问时就可能出现：

```text
No static resource hello
```

### `@GetMapping("/hello")`

它表示使用 HTTP GET 方法访问 `/hello` 时执行 `sayHello()`。

由于项目设置了：

```yaml
context-path: /api/v1
```

最终访问路径需要把两部分拼起来：

```text
/api/v1 + /hello = /api/v1/hello
```

## 七、打包项目

执行测试并打包：

```bash
./mvnw clean package
```

如果使用本机 Maven：

```bash
mvn clean package
```

打包成功后，生成的 JAR 文件位于：

```text
target/spring-demo-0.0.1-SNAPSHOT.jar
```

## 八、运行打包后的 JAR

```bash
java -jar target/spring-demo-0.0.1-SNAPSHOT.jar
```

然后访问：

```text
http://localhost:9000/api/v1/hello
```

也可以临时覆盖端口和上下文路径：

```bash
java -jar target/spring-demo-0.0.1-SNAPSHOT.jar \
  --server.port=8080 \
  --server.servlet.context-path=/demo
```

此时接口地址变为：

```text
http://localhost:8080/demo/hello
```

## 九、运行测试

运行全部测试：

```bash
./mvnw test
```

当前测试中的 `contextLoads()` 用于确认 Spring 应用上下文能够正常加载。后续可以继续增加 MockMvc 测试，验证 `/api/v1/hello` 的 HTTP 状态码和返回内容。

## 十、常见问题

### 1. 访问 `/hello` 返回 404

本项目配置了端口 `9000` 和上下文路径 `/api/v1`，正确地址是：

```text
http://localhost:9000/api/v1/hello
```

不要直接访问 `http://localhost:8080/hello`。

### 2. 出现 `No static resource hello`

优先检查：

1. 访问地址是否包含 `/api/v1`。
2. 启动类是否添加了 `@RestController`。
3. `@GetMapping("/hello")` 是否写在 Spring 管理的控制器类中。
4. 修改代码后是否重新启动了应用。

### 3. 端口 9000 已被占用

查看占用端口的进程：

```bash
lsof -i :9000
```

临时换端口启动：

```bash
./mvnw spring-boot:run -Dspring-boot.run.arguments="--server.port=9001"
```

然后访问：

```text
http://localhost:9001/api/v1/hello
```

### 4. `./mvnw` 没有执行权限

macOS 或 Linux 执行：

```bash
chmod +x mvnw
./mvnw spring-boot:run
```

## 十一、推荐学习顺序

完成本示例后，可以按下面顺序继续学习：

1. Controller、GET、POST、PUT、DELETE
2. 请求参数：`@RequestParam`、`@PathVariable`、`@RequestBody`
3. Service 层和 Repository 层
4. Spring Data JPA 和 MySQL
5. 参数校验与统一异常处理
6. RESTful API 设计
7. Spring Boot 测试
8. Spring Security 登录认证
