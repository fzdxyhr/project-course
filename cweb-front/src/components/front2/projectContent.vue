<template>
	<div class="kkm-project">
		<div class="container">
			<div class="row animate-box fadeInUp animated-fast">
				<div class="text-center fh5co-heading">
					<h2>专注提升开发效率</h2>
					<p>帮你降低成本，快速原型、快速沟通、快速展现缩短开发周期牢牢抓住商机.</p>
				</div>
			</div>
		</div>
		<div class="container-fluid proj-bottom">
			<div class="row">
				<el-row :gutter="20">
					<el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
						<div class="fh5co-project" data-animate-effect="fadeIn">
							<a href="http://demo.javafast.cn">
								<img src="/static/img/front2/work_10.png" alt="Free HTML5 Website Template by FreeHTML5.co" class="img-responsive">
								<h3>系统首页</h3>
								<span>查看演示</span>
							</a>
						</div>
					</el-col>
					<el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
						<div class="fh5co-project" data-animate-effect="fadeIn">
							<a href="http://demo.javafast.cn">
								<img src="/static/img/front2/work_11.png" alt="Free HTML5 Website Template by FreeHTML5.co" class="img-responsive">
								<h3>生成代码</h3>
								<span>查看演示</span>
							</a>
						</div>
					</el-col>
					<el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
						<div class="fh5co-project" data-animate-effect="fadeIn">
							<a href="http://demo.javafast.cn">
								<img src="/static/img/front2/work_12.png" alt="Free HTML5 Website Template by FreeHTML5.co" class="img-responsive">
								<h3>图形报表</h3>
								<span>查看演示</span>
							</a>
						</div>
					</el-col>
				</el-row>
				<el-row :gutter="20">
					<el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
						<div class="fh5co-project" data-animate-effect="fadeIn">
							<a href="http://demo.javafast.cn"><img src="/static/img/front2/work_15.png" alt="Free HTML5 Website Template by FreeHTML5.co"
								 class="img-responsive">
								<h3>列表页面</h3>
								<span>查看演示</span>
							</a>
						</div>
					</el-col>
					<el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
						<div class="fh5co-project" data-animate-effect="fadeIn">
							<a href="http://demo.javafast.cn"><img src="/static/img/front2/work_14.png" alt="Free HTML5 Website Template by FreeHTML5.co"
								 class="img-responsive">
								<h3>详情页面</h3>
								<span>查看演示</span>
							</a>
						</div>
					</el-col>
					<el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
						<div class="fh5co-project" data-animate-effect="fadeIn">
							<a href="http://demo.javafast.cn"><img src="/static/img/front2/work_17.png" alt="Free HTML5 Website Template by FreeHTML5.co"
								 class="img-responsive">
								<h3>编辑页面</h3>
								<span>查看演示</span>
							</a>
						</div>
					</el-col>
				</el-row>
			</div>
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
	.kkm-project {
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
		}

		.fh5co-heading p {
			font-size: 18px;
			line-height: 1.5;
			color: #828282;
		}

		.text-center {
			text-align: center;
		}

		h1,
		h2,
		h3,
		h4,
		h5,
		h6 {
			font-weight: 400;
		}

		.proj-bottom {
			padding-bottom: 4em;
		}

		.fh5co-project {
			margin-bottom: 30px;

			a {
				display: block;
				color: #000;
				position: relative;
				bottom: 0;
				overflow: hidden;
				-webkit-transition: 0.5s;
				-o-transition: 0.5s;
				transition: 0.5s;
			}

			a img {
				position: relative;
				-webkit-transition: 0.5s;
				-o-transition: 0.5s;
				transition: 0.5s;
			}

			a:hover {
				img {
					transform: scale(1.2);
				}
			}

			.img-responsive {
				display: block;
				max-width: 100%;
				height: auto;
			}

			a:after {
				opacity: 0;
				visibility: hidden;
				content: "";
				position: absolute;
				bottom: 0;
				left: 0;
				right: 0;
				top: 0;
				-webkit-box-shadow: inset 0px -34px 98px 8px rgba(0, 0, 0, 0.75);
				-moz-box-shadow: inset 0px -34px 98px 8px rgba(0, 0, 0, 0.75);
				-ms-box-shadow: inset 0px -34px 98px 8px rgba(0, 0, 0, 0.75);
				-o-box-shadow: inset 0px -34px 98px 8px rgba(0, 0, 0, 0.75);
				box-shadow: inset 0px -34px 98px 8px rgba(0, 0, 0, 0.75);
				z-index: 8;
				/* -webkit-transition: 0.5s; */
				-o-transition: 0.5s;
				/* transition: 0.5s; */
			}
		}

		.fh5co-project>a img {
			position: relative;
			-webkit-transition: 0.5s;
			-o-transition: 0.5s;
			transition: 0.5s;
		}

		.fh5co-project>a h3,
		.fh5co-project>a span {
			z-index: 12;
			position: absolute;
			right: 20px;
			left: 20px;
			bottom: 50px;
			color: #fff;
			margin: 0;
			padding: 0;
			opacity: 1;
			font-size: 30px;
			font-weight: 300;
			visibility: visible;
			-webkit-transition: 0.3s;
			-o-transition: 0.3s;
			transition: 0.3s;
		}

		.fh5co-project>a:hover {
			-webkit-box-shadow: 0px 18px 71px -10px rgba(0, 0, 0, 0.75);
			-moz-box-shadow: 0px 18px 71px -10px rgba(0, 0, 0, 0.75);
			box-shadow: 0px 18px 71px -10px rgba(0, 0, 0, 0.75);
		}

		a:-webkit-any-link {
			text-decoration: none;
		}

		a:hover {
			color: #fc8080;
			text-decoration: none;
		}

		.fh5co-project>a:after {
			opacity: 0;
			visibility: hidden;
			content: "";
			position: absolute;
			bottom: 0;
			left: 0;
			right: 0;
			top: 0;
			-webkit-box-shadow: inset 0px -34px 98px 8px rgba(0, 0, 0, 0.75);
			-moz-box-shadow: inset 0px -34px 98px 8px rgba(0, 0, 0, 0.75);
			-ms-box-shadow: inset 0px -34px 98px 8px rgba(0, 0, 0, 0.75);
			-o-box-shadow: inset 0px -34px 98px 8px rgba(0, 0, 0, 0.75);
			box-shadow: inset 0px -34px 98px 8px rgba(0, 0, 0, 0.75);
			z-index: 8;
			-webkit-transition: 0.5s;
			-o-transition: 0.5s;
			transition: 0.5s;
		}

		.fh5co-project>a span {
			font-size: 14px;
			bottom: 20px;
		}

		.container-fluid {
			margin-right: auto;
			margin-left: auto;
			padding-left: 15px;
			padding-right: 15px;
		}

		.features {
			margin-bottom: 3em;
			width: 100%;
			float: left;
		}

	}
</style>
