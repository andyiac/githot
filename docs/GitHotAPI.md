# GitHot API

## Repos

- repos 排序使用API
	
	按照Java,C,Objective-C,csharp,Python,PHP,JavaScript,Ruby进行分类
	
	github默认按照start量进行排序，各个语言排序接口如下：

		https://api.github.com/search/repositories?q=language:Java&page=1
		https://api.github.com/search/repositories?q=language:C&page=1
		https://api.github.com/search/repositories?q=language:Objective-C&page=1
		https://api.github.com/search/repositories?q=language:swift&page=1
		https://api.github.com/search/repositories?q=language:csharp&page=1
		https://api.github.com/search/repositories?q=language:Python&page=1
		https://api.github.com/search/repositories?q=language:PHP&page=1
		https://api.github.com/search/repositories?q=language:JavaScript&page=1
		https://api.github.com/search/repositories?q=language:Ruby&page=1
	注意：
	
	repository的详细信息和repository的owner信息都包含在此API返回的数据中。

## Trending repos

- trending repos 使用了第三方的一个API

		http://trending.codehub-app.com/trending
		
- trending repos 跟github 一样trending 是有三个

		http://trending.codehub-app.com/v2/trending?language=c&since=daily
		http://trending.codehub-app.com/v2/trending?language=c&since=weekly
		http://trending.codehub-app.com/v2/trending?language=c&since=monthly
		

## Users
- users 排序使用API

	按照Java,C,Objective-C,csharp,Python,PHP,JavaScript,Ruby进行分类

		https://api.github.com/search/users?q=location:china&page=1
		https://api.github.com/search/users?q=language:Java+followers:>500&page=1
		https://api.github.com/search/users?q=language:C+followers:>500&page=1
		...
	注意：
	
	users排序比repos多加了一个按照地域location:china的分类，放在了第一项
	
		https://api.github.com/search/users?q=location:china&page=1
	
	另外，q=queryParams 中的queryParams需要进行URLencoding 如：
	
		https://api.github.com/search/users?q=language:Java+followers:%3E500&page=1
	
- 某一个用户信息,比如如andyiac的详细详细：

		https://api.github.com/users/andyiac/repos

## check update 
- 检查更新是通过使用 fir.im 提供的更新接口

		http://api.fir.im/apps/latest/55fd648a00fc7453bd000002?api_token=e9643ae1e41c57d6d829504ce8f9f90a


