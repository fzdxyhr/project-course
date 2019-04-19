<template>
	<div class="kkm-blog">
		<div class="container">
			<div class="text-center fh5co-heading">
				<h2>企业为什么需要快速开发平台</h2>
				<p>JavaFast团队为您提供了一整套的应用开发过程中所有可能需的基础功能，让您的团队从那种重复性工作中脱离出来，专注于产品的体验与设计工作，为企业创建更有价值的产品</p>
			</div>
			<el-row :gutter="20">
				<el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
					<div class="fh5co-blog animate-box fadeInUp animated-fast">
						<a href="#"><img class="img-responsive" src="/static/img/front2/project-4.jpg" alt=""></a>
						<div class="blog-text">
							<h3><a href="">软件项目成本过高，开发周期过长</a></h3>

							<p>基于代码生成器的开发方式，可以将编码工作量减少80%以上，为企业提高开发效率，为企业节省项目研发成本，减少开发周期.</p>
							<br>
							<a href="http://demo.javafast.cn" target="_blank" class="btn btn-primary">查看演示</a>
						</div>
					</div>
				</el-col>
				<el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
					<div class="fh5co-blog animate-box fadeInUp animated-fast">
						<a href="#"><img class="img-responsive" src="/static/img/front2/project-2.jpg" alt=""></a>
						<div class="blog-text">
							<h3><a href="">项目需求经常变更，加班无法完成</a></h3>

							<p> 基于代码生成器的智能快速开发平台，可以帮助解决java项目中80%的重复工作，让开发者更多关注业务逻辑.</p>
							<br>
							<a href="http://demo.javafast.cn" target="_blank" class="btn btn-primary">查看演示</a>
						</div>
					</div>
				</el-col>
				<el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
					<div class="fh5co-blog animate-box fadeInUp animated-fast">
						<a href="#"><img class="img-responsive" src="/static/img/front2/project-3.jpg" alt=""></a>
						<div class="blog-text">
							<h3><a href="">技术人员参差不齐，产品BUG太多</a></h3>

							<p> JavaFast基于流行的JAVA开源技术上构建，扩展容易，学习成本低，可快速构建企业级项目，即满足企业项目实现快速开发的需求.</p>
							<br>
							<a href="http://demo.javafast.cn" target="_blank" class="btn btn-primary">查看演示</a>
						</div>
					</div>
				</el-col>
			</el-row>
		</div>
	</div>
</template>

<script>
	export default {
		data() {
			return {

			};
		}
	}
</script>

<style lang="scss">
	.kkm-blog {
		padding: 7em 0;
		clear: both;

		.container {
			width: 1200px;
			margin: 0 auto;
			padding: 0;
		}

		.fh5co-heading {
			margin-bottom: 5em;
		}

		.fh5co-heading h2 {
			font-size: 32px;
			margin-bottom: 20px;
			line-height: 1.5;
			color: #000;
			font-weight: 400;
			font-family: "Work Sans", Arial, sans-serif;
		}

		.fh5co-heading p {
			font-size: 18px;
			line-height: 1.5;
			color: #828282;
		}

		.text-center {
			text-align: center;
		}

		.fh5co-blog {
			margin-bottom: 60px;
			width: 100%;
			padding-left: 15px;
			padding-right: 15px;

			a {
				display: block;
				position: relative;
				-webkit-transition: 0.5s;
				-o-transition: 0.5s;
				transition: 0.5s;
			}

			a img {
				width: 100%;
			}

			.blog-text {
				margin-bottom: 30px;
				position: relative;
				background: #fff;
				padding: 30px;
				float: left;
				-webkit-box-shadow: 0px 10px 20px -12px rgba(0, 0, 0, 0.18);
				-moz-box-shadow: 0px 10px 20px -12px rgba(0, 0, 0, 0.18);
				box-shadow: 0px 10px 20px -12px rgba(0, 0, 0, 0.18);
			}

			.blog-text h3 {
				font-size: 20px;
				margin-bottom: 20px;
				line-height: 1.5;
				font-weight: 400;
				color: #000;
				font-family: "Work Sans", Arial, sans-serif;
			}

			.blog-text p {
				font-family: "Microsoft Yahei", "HanHei SC", PingHei, "PingFang SC", "Helvetica Neue", Helvetica, Arial, "Hiragino Sans GB", "Heiti SC", "WenQuanYi Micro Hei", sans-serif;
				font-weight: 400;
				color: #333;
				outline: 0;
				speak: none;
				font-size: 14px;
				line-height: 1.7;
			}

			.blog-text h3 a {
				color: black;
			}

			.btn-primary {
				background: #EA9215;
				color: #fff;
				border: 2px solid #EA9215;
			}

			.btn {
				width: 68px;
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
		}

		.img-responsive {
			display: block;
			max-width: 100%;
			height: auto;
		}

		a:-webkit-any-link {
			text-decoration: none;
		}
	}
</style>
