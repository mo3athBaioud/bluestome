1.使用AOP技术对需要拦截的方法进行切面配置。
  规范DAO方法,必须以一下方式为方法前缀，例如findById(),updateBean,deleteBy...
  a.增 insert
  b.删 delete
  c.改 update
  d.查 find