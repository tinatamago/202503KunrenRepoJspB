<%@ page contentType="text/html ; charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<meta charset="UTF-8">
<meta name="description" content="〇〇システム">
<meta name="viewport" content="width=device-width">
<meta http-equiv="Content-Script-Type" content="text/javascript">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">

<title>メニュー</title>
<style>
	body{
		height:100vh;
	}
	.menuImg{
		object-fit:cover;
		height:200px;
	}
	.menuTxt{
		height:calc(100% - 200px);
	}
	.bg-sec{
		background-color: #efeed4;
	}
	.bg-image{
		background-position: center;
		background-size: cover;
	}
	.bg-filter{
			background: linear-gradient(45deg, #00b8ffad, #ffe000d4);
	}
	.rounded {
	    border-radius: 15px !important;
	}
</style>
</head>

<body class="d-flex flex-column">
<header class="bg-dark" id="header">
	<div class="row justify-content-between p-3">
		<h5 class="my-2 col text-white font-weight-bold">グループＢ　成果物</h5>
		<div class="col-auto row mx-0">
			<h5 class="my-2 col text-white px-2">ＩＤ:${userId}</h5>
			<a type="button" href="/202503KunrenRepoJspB/Login" class="btn btn-warning font-weight-bold">Logout</a>
		</div>
	</div>
</header>

<div class="bg-image" style="background-image:url('https://source.unsplash.com/gMsnXqILjp4');">
	<div class="bg-filter py-5">
		<div class="container py-5">	
			<h1 class="text-white font-weight-bold mb-0 display-2">
			「グループＢ」
			<br>
			<span class="h1 font-weight-bold text-white	">Welcome to Menu<br></span>
			</h1>
			<ul class="bg-white col-auto px-5 py-4 my-4 rounded">
				<li class="h4 font-weight-bold">Java</li>
				<li class="h4 font-weight-bold">JSP/Servlet</li>
				<li class="h4 font-weight-bold">Spring</li>
				<li class="h4 font-weight-bold">PostgreSQL</li>
				<li class="h4 font-weight-bold">GitHub</li>
				<li class="h4 font-weight-bold">etc...</li>
			</ul>
		</div>
	</div>
</div>
<div class="container my-5">
	<div>
		<h2 class="font-weight-bold border-bottom pb-2">MENU</h2>
		<h5 class="font-weight-bold text-secondary pb-2">グループＢ　成果物一覧です。</h5>
	</div>
	
	<div class="row mt-3">
		<div class ="col-md-4 col-12 my-3">
			<div class="bg-sec shadow border h-auto">
				<img class="w-100 menuImg" src="/202503KunrenRepoJspB/img/no_image.jpeg">
				<div class="row mx-0 p-3 menuTxt">
					<h4 class="border-bottom font-weight-bold w-100">Ｎｏ.１ シュラインシステムin北海道</h4>
					<p class="text-secondary　w-100 mb-1">
						〇〇の検索や登録できます。
					</p>
					<p class="text-secondary w-100 mb-2">
					作成者　：<br>
					開発環境：JSP/Servlet
					</p>
					<form name="form1" action="DspShrineSearch" method="post">
						<a type="button" href="#"
							onclick="document.form1.submit();"
							class="btn btn-outline-primary font-weight-bold col mt-auto">ページを見る</a>
					</form>
				</div>
			</div>
		</div>		
		<div class ="col-md-4 col-12 my-3">
			<div class="bg-sec shadow border h-auto">
				<img class="w-100 menuImg" src="/202503KunrenRepoJspB/img/no_image.jpeg">
				<div class="row mx-0 p-3 menuTxt">
					<h4 class="border-bottom font-weight-bold w-100">Ｎｏ.２ 小樽観光システム</h4>
					<p class="text-secondary　w-100 mb-1">
						〇〇の検索や登録できます。
					</p>
					<p class="text-secondary w-100 mb-2">
					作成者　：<br>
					開発環境：JSP/Servlet
					</p>
					<form name="form2" action="DspOtaruSightseeingSearch" method="post">
						<a type="button" href="#"
							onclick="document.form2.submit();"
							class="btn btn-outline-primary font-weight-bold col mt-auto">ページを見る</a>
					</form>
				</div>
			</div>
		</div>

		<div class ="col-md-4 col-12 my-3">
			<div class="bg-sec shadow border h-auto">
				<img class="w-100 menuImg" src="/202503KunrenRepoJspB/img/no_image.jpeg">
				<div class="row mx-0 p-3 menuTxt">
					<h4 class="border-bottom font-weight-bold w-100">Ｎｏ.３ バリアフリー施設情報システム</h4>
					<p class="text-secondary　w-100 mb-1">
						〇〇の検索や登録できます。
					</p>
					<p class="text-secondary w-100 mb-2">
					作成者　：<br>
					開発環境：JSP/Servlet
					</p>
					<form name="form3" action="DspBarrierFreeSearch" method="post">
						<a type="button" href="#"
							onclick="document.form3.submit();"
							class="btn btn-outline-primary font-weight-bold col mt-auto">ページを見る</a>
					</form>
				</div>
			</div>
		</div>
		<div class ="col-md-4 col-12 my-3">
			<div class="bg-sec shadow border h-auto">
				<img class="w-100 menuImg" src="/202503KunrenRepoJspB/img/no_image.jpeg">
				<div class="row mx-0 p-3 menuTxt">
					<h4 class="border-bottom font-weight-bold w-100">Ｎｏ.４ 観光地検索システム</h4>
					<p class="text-secondary　w-100 mb-1">
						〇〇の検索や登録できます。
					</p>
					<p class="text-secondary w-100 mb-2">
					作成者　：<br>
					開発環境：JSP/Servlet
					</p>
					<form name="form4" action="DspKankoSearch" method="post">
						<a type="button" href="#"
							onclick="document.form4.submit();"
							class="btn btn-outline-primary font-weight-bold col mt-auto">ページを見る</a>
					</form>
				</div>
			</div>
		</div>
		<div class ="col-md-4 col-12 my-3">
			<div class="bg-sec shadow border h-auto">
				<img class="w-100 menuImg" src="/202503KunrenRepoJspB/img/no_image.jpeg">
				<div class="row mx-0 p-3 menuTxt">
					<h4 class="border-bottom font-weight-bold w-100">Ｎｏ.５ ポケモンカード検索システム</h4>
					<p class="text-secondary　w-100 mb-1">
						〇〇の検索や登録できます。
					</p>
					<p class="text-secondary w-100 mb-2">
					作成者　：<br>
					開発環境：JSP/Servlet
					</p>
					<form name="form5" action="DspPtcgCardsSearch" method="post">
						<a type="button" href="#"
							onclick="document.form5.submit();"
							class="btn btn-outline-primary font-weight-bold col mt-auto">ページを見る</a>
					</form>
				</div>
			</div>
		</div>

		<div class="col-md-4 col-12 my-3">
			<div class="bg-sec shadow border h-auto">
				<img class="w-100 menuImg" src="/202503KunrenRepoJspB/img/no_image.jpeg">
				<div class="row mx-0 p-3 menuTxt">
					<h4 class="border-bottom font-weight-bold w-100">Ｎｏ.６ ドラゴンボールシステム</h4>
					<p class="text-secondary　w-100 mb-1">
						〇〇の検索や登録できます。
					</p>
					<p class="text-secondary w-100 mb-2">
					作成者　：<br>
					開発環境：JSP/Servlet
					</p>
					<form name="form6" action="DspDbSearch" method="post">
						<a type="button" href="#"
							onclick="document.form6.submit();"
							class="btn btn-outline-primary font-weight-bold col mt-auto">ページを見る</a>
					</form>
				</div>
			</div>
		</div>

		<div class ="col-md-4 col-12 my-3">
			<div class="bg-sec shadow border h-auto">
				<img class="w-100 menuImg" src="/202503KunrenRepoJspB/img/no_image.jpeg">
				<div class="row mx-0 p-3 menuTxt">
					<h4 class="border-bottom font-weight-bold w-100">Ｎｏ.７ 旅行で使える韓国語システム</h4>
					<p class="text-secondary　w-100 mb-1">
						〇〇の検索や登録できます。
					</p>
					<p class="text-secondary w-100 mb-2">
					作成者　：<br>
					開発環境：JSP/Servlet
					</p>
					<form name="form7" action="DspKoreanSearch" method="post">
						<a type="button" href="#"
							onclick="document.form7.submit();"
							class="btn btn-outline-primary font-weight-bold col mt-auto">ページを見る</a>
					</form>
				</div>
			</div>
		</div>

		<div class ="col-md-4 col-12 my-3">
			<div class="bg-sec shadow border h-auto">
				<img class="w-100 menuImg" src="/202503KunrenRepoJspB/img/no_image.jpeg">
				<div class="row mx-0 p-3 menuTxt">
					<h4 class="border-bottom font-weight-bold w-100">Ｎｏ.８ 花の種類検索システム</h4>
					<p class="text-secondary　w-100 mb-1">
						〇〇の検索や登録できます。
					</p>
					<p class="text-secondary w-100 mb-2">
					作成者　：<br>
					開発環境：JSP/Servlet
					</p>
					<form name="form8" action="DspFlowerSearch" method="post">
						<a type="button" href="#"
							onclick="document.form8.submit();"
							class="btn btn-outline-primary font-weight-bold col mt-auto">ページを見る</a>
					</form>
				</div>
			</div>
		</div>

		<div class ="col-md-4 col-12 my-3">
			<div class="bg-sec shadow border h-auto">
				<img class="w-100 menuImg" src="/202503KunrenRepoJspB/img/no_image.jpeg">
				<div class="row mx-0 p-3 menuTxt">
					<h4 class="border-bottom font-weight-bold w-100">Ｎｏ.９ カップ焼きそばシリーズシステム</h4>
					<p class="text-secondary　w-100 mb-1">
						〇〇の検索や登録できます。
					</p>
					<p class="text-secondary w-100 mb-2">
					作成者　：<br>
					開発環境：JSP/Servlet
					</p>
					<form name="form9" action="DspYakisobaSearch" method="post">
						<a type="button" href="#"
							onclick="document.form9.submit();"
							class="btn btn-outline-primary font-weight-bold col mt-auto">ページを見る</a>
					</form>
				</div>
			</div>
		</div>

		<div class ="col-md-4 col-12 my-3">
			<div class="bg-sec shadow border h-auto">
				<img class="w-100 menuImg" src="/202503KunrenRepoJspB/img/no_image.jpeg">
				<div class="row mx-0 p-3 menuTxt">
					<h4 class="border-bottom font-weight-bold w-100">Ｎｏ.10 北海道ポケふた照会システム</h4>
					<p class="text-secondary　w-100 mb-1">
						〇〇の検索や登録できます。
					</p>
					<p class="text-secondary w-100 mb-2">
					作成者　：<br>
					開発環境：JSP/Servlet
					</p>
					<form name="form10" action="DspPokeTypeSearch" method="post">
						<a type="button" href="#"
							onclick="document.form10.submit();"
							class="btn btn-outline-primary font-weight-bold col mt-auto">ページを見る</a>
					</form>
				</div>
			</div>
		</div>
									
	</div>
</div>
<footer class="bg-dark w-100 d-block mb-0 py-4 mt-auto" id="footer">
	<a class="text-white d-block text-center">©2024 Trust Pro Inc.</a>
</footer>
</body>
</html>