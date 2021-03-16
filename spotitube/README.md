# Spotitube

## Database
`context.xml`
```
<Resource
 28          name="jdbc/spotitube"
 29          auth="Container"
 30          type="javax.sql.DataSource"
 31          maxTotal="10"
 32          maxIdle="10"
 33          maxWaitMillis="10000"
 34          driverClassName="com.mysql.cj.jdbc.Driver"
 35          url="jdbc:mysql://localhost:3306/spotitube_db"
 36          username="spotitube_user"
 37          password="xiG#4xHq!JD3f#rhK#K"
 38     />
```