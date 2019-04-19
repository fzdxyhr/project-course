<template>
	<div class="kkm-steps">
		<div class="container">
			<div class="text-center kkm-heading">
				<h2>开启智能快速开发的新时代</h2>
				<p>基于代码生成器的智能快速开发平台，可以帮助解决java项目中80%的重复工作，让开发者更多关注业务逻辑，让软件开发极限提速，原本需要开发5个月的项目，1个月即可完成.</p>
			</div>

			<div class="bs-wizard animate-box fadeInUp animated-fast" style="border-bottom:0;margin-top: 8%;">
				<div class="col-xs-3 bs-wizard-step complete">
					<div class="text-center bs-wizard-stepnum">
						<h4>第一步</h4>
					</div>
					<div class="progress">
						<div class="progress-bar"></div>
					</div>
					<a href="#" class="bs-wizard-dot"></a>
					<div class="bs-wizard-info text-center">
						<p>选择需要的版本</p>
					</div>
				</div>

				<div class="col-xs-3 bs-wizard-step complete">
					<!-- complete -->
					<div class="text-center bs-wizard-stepnum">
						<h4>第二步</h4>
					</div>
					<div class="progress">
						<div class="progress-bar"></div>
					</div>
					<a href="#" class="bs-wizard-dot"></a>
					<div class="bs-wizard-info text-center">
						<p>联系客服签约</p>
					</div>
				</div>

				<div class="col-xs-3 bs-wizard-step complete">
					<!-- complete -->
					<div class="text-center bs-wizard-stepnum">
						<h4>第三步</h4>
					</div>
					<div class="progress">
						<div class="progress-bar"></div>
					</div>
					<a href="#" class="bs-wizard-dot"></a>
					<div class="bs-wizard-info text-center">
						<p>下载合同并在线支付</p>
					</div>
				</div>

				<div class="col-xs-3 bs-wizard-step active">
					<!-- active -->
					<div class="text-center bs-wizard-stepnum">
						<h4>完成</h4>
					</div>
					<div class="progress">
						<div class="progress-bar"></div>
					</div>
					<a href="#" class="bs-wizard-dot"></a>
					<div class="bs-wizard-info text-center">
						<p>获取产品和技术支持服务</p>
					</div>
				</div>
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
	.kkm-steps {
		clear: both;
		padding-bottom: 7em;

		.container {
			width: 1200px;
			margin: 0 auto;
			padding: 0;
		}

		.kkm-heading {
			margin-bottom: 5em;
		}

		.text-center {
			text-align: center;
		}

		.kkm-heading h2 {
			font-size: 32px;
			margin-bottom: 20px;
			line-height: 1.5;
			color: #000;
		}

		h2,
		h6 {
			font-weight: 400;
		}

		h2,
		figure {
			color: #000;
			font-family: "Work Sans", Arial, sans-serif;
			font-weight: 400;
			margin: 0 0 20px 0;
		}

		.kkm-heading p {
			font-size: 18px;
			line-height: 1.5;
			color: #828282;
		}

		.bs-wizard {
			border-bottom: solid 1px #e0e0e0;
			padding: 0 0 10px 0;
			margin-top: 40px;

			.bs-wizard-step {
				padding: 0;
				position: relative;
			}

			.bs-wizard-step .bs-wizard-stepnum {
				font-size: 16px;
				margin-bottom: 10px;
			}

			.bs-wizard-step:first-child>.progress {
				left: 50%;
				width: 50%;
			}

			.bs-wizard-step>.progress {
				position: relative;
				border-radius: 0px;
				height: 8px;
				box-shadow: none;
				margin: 22px 0;
			}

			.progress {
				overflow: hidden;
				height: 20px;
				margin-bottom: 20px;
				background-color: #f5f5f5;
				border-radius: 4px;
				-webkit-box-shadow: inset 0 1px 2px rgba(0, 0, 0, 0.1);
				box-shadow: inset 0 1px 2px rgba(0, 0, 0, 0.1);
			}

			.bs-wizard-step.complete>.progress>.progress-bar {
				width: 100%;
			}

			.bs-wizard-step:last-child.active>.progress>.progress-bar {
				width: 100%;
			}

			.bs-wizard-step .progress .progress-bar {
				width: 0px;
				box-shadow: none;
				background: #fbe8aa;
			}

			.progress-bar {
				float: left;
				width: 0%;
				height: 100%;
				font-size: 12px;
				line-height: 20px;
				color: #fff;
				text-align: center;
				background-color: #337ab7;
				-webkit-box-shadow: inset 0 -1px 0 rgba(0, 0, 0, 0.15);
				box-shadow: inset 0 -1px 0 rgba(0, 0, 0, 0.15);
				-webkit-transition: width 0.6s ease;
				-o-transition: width 0.6s ease;
				transition: width 0.6s ease;
			}

			.bs-wizard-step>.bs-wizard-dot {
				position: absolute;
				width: 30px;
				height: 30px;
				display: block;
				background: #fbe8aa;
				top: 45px;
				left: 50%;
				margin-top: -15px;
				margin-left: -15px;
				border-radius: 50%;
			}

			a:-webkit-any-link {
				text-decoration: none;
			}

			.bs-wizard-step .bs-wizard-info {
				color: #999;
				font-size: 14px;
				padding: 20px;
			}

			.bs-wizard-step>.bs-wizard-dot:after {
				content: ' ';
				width: 14px;
				height: 14px;
				background: #fbbd19;
				border-radius: 50px;
				position: absolute;
				top: 8px;
				left: 8px;
			}

			.col-xs-3 {
				width: 25%;
				float: left;
			}

			.bs-wizard-step:last-child>.progress {
				width: 50%;
			}
		}


	}
</style>
