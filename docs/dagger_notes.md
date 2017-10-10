# dagger1

## 文档

- [dagger](http://square.github.io/dagger/)
- [官方demo](https://github.com/square/dagger/)


## use flow

首先在 Application 中构建图 `ObjectGraph` 对象图

```
applicationGraph = ObjectGraph.create(getModules().toArray());
```

同时提供方 `getModules()` 方法

A list of modules to use for the application graph.
Subclasses can override this method to provide additional modules
provided they call {@code super.getModules()}.

```
protected List<Object> getModules() {
  return Arrays.<Object>asList(new AndroidModule(this));
}
```

另外还提供 `inject(Object object)` 方法，提供加入到 `ObjectGraph` 的方法。

（ githot 中通过在ClientApplication实现接口设计，在实现比较精妙）
