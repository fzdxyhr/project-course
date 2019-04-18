<template>
	<div class="kmm-index">
		<div class="fh5co-nav">
			<div class="top-menu">
				<div class="container">
					<div class="row">
						<div class="col-xs-3">
							<div id="fh5co-logo"><a href="index.html"><img src="/static/img/front2/javafast.png" style="width:45px;">
									JavaFast<span>.</span></a></div>
						</div>
						<div class="col-xs-9 text-right menu-1">
							<ul>
								<li class="active"><a href="index.html">首页</a></li>
								<li><a href="courses.html">产品介绍</a></li>
								<li><a href="pricing.html">功能套餐</a></li>
								<li><a href="detail.html">技术特点</a></li>
								<li><a href="blog.html">常见问题</a></li>
								<li><a href="contact.html">联系我们</a></li>
								<li class="btn-cta"><a href="http://wpa.qq.com/msgrd?v=3&amp;uin=1553294881&amp;site=qq&amp;menu=yes" target="_blank"><span>购买咨询</span></a></li>
								<li class="btn-cta"><a href="http://demo.javafast.cn" target="_blank"><span>在线演示</span></a></li>
							</ul>
						</div>
					</div>

				</div>
			</div>
		</div>
		<div class="fh5co-cover" id="fh5co-header" style="background-image:url(/static/img/front2/img_bg_1.jpg);background-position: 50% 0%;">
			<div class="overlay"></div>
			<div class="container">
				<div class="row">
					<div class="col-md-8 col-md-offset-2 text-center">
						<div class="display-t">
							<div class="display-tc animate-box fadeIn animated-fast" data-animate-effect="fadeIn">
								<h1>Java智能快速开发平台</h1>
								<p>
									<a class="btn btn-primary btn-lg btn-learn" href="http://wpa.qq.com/msgrd?v=3&amp;uin=1553294881&amp;site=qq&amp;menu=yes"
									 target="_blank">
										<i class="icon-qq"></i>
										购买咨询
									</a>
									<a class="btn btn-primary btn-lg  btn-video" href="http://demo.javafast.cn" target="_blank">
										<i class="icon-play"></i>
										在线演示
									</a>
								</p>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</template>

<script>
</script>

<style lang="scss">
	.kmm-index {
		.fh5co-nav {
			position: absolute;
			top: 0;
			margin: 0;
			padding: 0;
			width: 100%;
			padding: 0;
			z-index: 1001;

			.top-menu {
				padding: 28px 0;
			}

			.container {
				width: 1200px;
				margin: 0 auto;
				padding: 0;
			}

			.row {
				margin-left: -15px;
				margin-right: -15px;
			}

			#fh5co-logo {
				font-size: 24px;
				margin: 0;
				padding: 0;
				text-transform: uppercase;
				font-weight: bold;
				font-weight: 700;
				font-family: "Work Sans", Arial, sans-serif;
			}

			a {
				padding: 5px 10px;
				color: #fff;
			}

			img {
				vertical-align: middle;
				border: 0;
			}

			#fh5co-logo a span {
				color: #EA9215;
			}

			.col-xs-3 {
				width: 25%;
				float: left;
			}

			.col-xs-9 {
				width: 75%;
				float: left;
			}

			.text-right {
				text-align: right;
			}

			ul {
				padding: 0;
				margin: 5px 0 0 0;
			}

			ul li {
				padding: 0;
				margin: 0;
				list-style: none;
				display: inline;
			}

			ul li.active>a {
				color: #fff !important;
			}

			ul li a {
				font-size: 15px;
				padding: 30px 15px;
				color: rgba(255, 255, 255, 0.5);
				-webkit-transition: 0.5s;
				-o-transition: 0.5s;
				transition: 0.5s;
			}

			ul li.btn-cta a {
				padding: 30px 0px !important;
				color: #fff;
			}

			ul li.btn-cta a span {
				background: rgba(255, 255, 255, 0.2);
				padding: 4px 10px;
				display: -moz-inline-stack;
				display: inline-block;
				zoom: 1;
				*display: inline;
				-webkit-transition: 0.3s;
				-o-transition: 0.3s;
				transition: 0.3s;
				-webkit-border-radius: 100px;
				-moz-border-radius: 100px;
				-ms-border-radius: 100px;
				border-radius: 100px;
			}
		}

		#fh5co-cover {
			background-size: cover;
			background-position: top center;
			background-repeat: no-repeat;
			position: relative;
		}

		.fh5co-cover {
			height: 800px;
			background-size: cover;
			background-position: top center;
			background-repeat: no-repeat;
			position: relative;
			float: left;
			width: 100%;

			.fadeIn {
				-webkit-animation-name: fadeIn;
				animation-name: fadeIn;
			}

			.animated-fast {
				-webkit-animation-duration: .5s;
				animation-duration: .5s;
				-webkit-animation-fill-mode: both;
				animation-fill-mode: both;
			}

			.display-tc h1 {
				margin-bottom: 20px;
				font-size: 54px;
				line-height: 1.3;
				font-weight: 300;
				color: white;
			}

			.display-tc .btn.btn-learn {
				background: rgba(234, 146, 21, 0.8);
				border: none;
			}

			.display-tc .btn {
				padding: 15px 30px;
				color: #fff;
				border: none !important;
				font-size: 18px;
			}

			.display-tc .btn.btn-video {
				background: rgba(9, 198, 171, 0.8);
				border: none;
			}

			.btn.btn-lg {
				padding: 18px 36px !important;
			}

			.btn-primary {
				background: #EA9215;
				color: #fff;
				border: 2px solid #EA9215;
			}

			.display-tc {
				display: table-cell !important;
				vertical-align: middle;
			}

			.btn-lg,
			.btn-group-lg>.btn {
				padding: 10px 20px;
				font-size: 18px;
				line-height: 1.33333;
				border-radius: 6px;
			}
			
			.btn {
				margin-right: 4px;
				margin-bottom: 4px;
				font-family: "Work Sans", Arial, sans-serif;
				font-size: 16px;
				font-weight: 400;
				-webkit-border-radius: 30px;
				-moz-border-radius: 30px;
				-ms-border-radius: 30px;
				border-radius: 30px;
				-webkit-transition: 0.5s;
				-o-transition: 0.5s;
				transition: 0.5s;
				padding: 8px 20px;
			}
			

			.container {
				width: 1200px;
				margin: 0 auto;
				padding: 0;

				.row {
					margin-left: -15px;
					margin-right: -15px;
				}
			}

			.text-center {
				text-align: center;
			}

			.overlay {
				z-index: 0;
				position: absolute;
				bottom: 0;
				top: 0;
				left: 0;
				right: 0;
				background: rgba(0, 0, 0, 0.4);
			}
			
			.display-tc,
			.display-t {
				z-index: 90;
				height: 700px;
				display: table;
				width: 100%;
				margin-top: 100px;
			}
		}

	}
</style>
