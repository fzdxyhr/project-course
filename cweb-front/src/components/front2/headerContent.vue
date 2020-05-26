<template>
	<div class="header-index">
		<div class="fh5co-nav">
			<div class="top-menu">
				<div class="container">
					<div class="col-xs-3">
						<div id="fh5co-logo">
							<a href="index.html">
								<img src="/static/img/front2/javafast.png" style="width:45px;" />
								快快码<span>.</span>
							</a>
						</div>
					</div>
					<div class="col-xs-9 text-right menu-1">
						<ul>
							<li><a href="javaScript:" @click="go2Page('test')">首页</a></li>
							<!-- <li><a href="courses.html">产品介绍</a></li> -->
							<li :class="menu === 'function'?'active':''"><a href="javaScript:" @click="go2Page('function')">功能套餐</a></li>
							<li :class="menu === 'science'?'active':''"><a href="javaScript:" @click="go2Page('science')">技术特点</a></li>
							<li :class="menu === 'question'?'active':''"><a href="javaScript:" @click="go2Page('question')">常见问题</a></li>
							<li :class="menu === 'attach'?'active':''"><a href="javaScript:" @click="go2Page('attach')">联系我们</a></li>
							<li class="btn-cta"><a href="http://wpa.qq.com/msgrd?v=3&amp;uin=1553294881&amp;site=qq&amp;menu=yes" target="_blank"><span>购买咨询</span></a></li>
							<li class="btn-cta"><a href="http://demo.javafast.cn" target="_blank"><span>在线演示</span></a></li>
						</ul>
					</div>
				</div>
			</div>
		</div>
		<div class="fh5co-cover" style="background-image:url(/static/img/front2/img_bg_1.jpg);background-position: 50% 0%;">
			<div class="overlay"></div>
			<div class="display-t">
				<div v-if="menu ==='function'" class="display-tc animate-box fadeIn animated-fast" data-animate-effect="fadeIn">
					<h1>Java智能快速开发平台</h1>
					<p style="margin-top: 3%;font-size: 20px;line-height: 1.5;">
						<span style="color: white;">让软件开发极限提速</span>
						<span class="detail-demo">查看演示</span>
					</p>
				</div>
				<div v-if="menu ==='science'" class="display-tc animate-box fadeIn animated-fast" data-animate-effect="fadeIn">
					<h1>技术特点</h1>
					<p style="margin-top: 3%;font-size: 20px;line-height: 1.5;">
						<span style="color: white;">技术特点介绍</span>
						<span class="detail-demo">联系客服</span>
					</p>
				</div>
				<div v-if="menu ==='question'" class="display-tc animate-box fadeIn animated-fast" data-animate-effect="fadeIn">
					<h1>常见问题</h1>
					<p style="margin-top: 3%;font-size: 20px;line-height: 1.5;">
						<span style="color: white;">JavaFast咨询常见问题参考</span>
						<span class="detail-demo">联系客服</span>
					</p>
				</div>
				<div v-if="menu ==='attach'" class="display-tc animate-box fadeIn animated-fast" data-animate-effect="fadeIn">
					<h1>联系我们</h1>
					<p style="margin-top: 3%;font-size: 20px;line-height: 1.5;">
						<span style="color: white;">让软件开发极限提速</span>
						<span class="detail-demo">立即咨询</span>
					</p>
				</div>
			</div>
		</div>
	</div>
</template>

<script>
	export default {
		props: {
			menu: {
				type: String,
				default: "function"
			},
		},
		data() {
			return {

			};
		},
		methods: {
			go2Page(page) {
				this.$router.push({
					name: page
				})
			}
		},
	}
</script>

<style lang="scss">
	.header-index {
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

		.fh5co-cover {
			height: 600px;
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

			.display-tc {
				display: table-cell !important;
				vertical-align: middle;
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
				text-align: center;
				position: absolute;
				left: 0;
				right: 0;
				width: 100%;
				margin-top: 11%;
			}

			.detail-demo {
				cursor: pointer;
				color: #aba3a3;
			}

			.detail-demo:hover {
				color: #fc8080;
				cursor: pointer;
			}
		}
	}
</style>
