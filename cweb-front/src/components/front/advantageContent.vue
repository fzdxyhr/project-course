<template>
	<div class="advantage-index">
		<h2 class="index-module-title">搭搭云的优势</h2>
		<div class="graphic clearfix">
			<el-row :gutter="20" style="width: 100%;">
				<el-col :span="8">
					<div class="item">
						<div class="image">
							<img src="/static/img/front/14.png" alt="灵活应变">
						</div>
						<div class="small-title">灵活应变</div>
						<div class="text">软件功能实时调整，边用边改、即改即用</div>
					</div>
				</el-col>
				<el-col :span="8">
					<div class="item">
						<div class="image">
							<img src="/static/img/front/15.png" alt="功能强大">
						</div>
						<div class="small-title">功能强大</div>
						<div class="text">能满足复杂业务需求，相比流行的
							<br>移动办公APP或SaaS软件，弥补了其功能单一的不足
						</div>
					</div>
				</el-col>
				<el-col :span="8">
					<div class="item ie8">
						<div class="image">
							<img src="/static/img/front/16.png" alt="易扩展">
						</div>
						<div class="small-title">易扩展</div>
						<div class="text">可自由对接其他软件和硬件系统</div>
					</div>
				</el-col>
			</el-row>
		</div>
		<div class="case">
			<h3>案例：</h3>
			<div class="item">上海某化妆品贸易有限公司因订单较多，必须使用软件系统来管理业务，前后购买过两次软件，都因为业务变化过快，软件修改太慢，结果无人使用造成浪费。使用搭搭云后，每次业务发生变化时都能快速调整电脑和手机端的软件功能，充分体现了搭搭云在灵活性方面的优势。</div>
			<div class="item">广东佛山某家具厂，因业务系统变化繁多，多年来持续在与财务系统对接上投入了大量资金。业务系统切换至搭搭云后，充分利用了搭搭云动态技术的优势，不动代码就可以调整与财务系统的接口，降低了成本，提高了效率和灵活性。</div>
		</div>
	</div>
</template>

<script>
</script>

<style lang="scss">
	.advantage-index {
		color: #fff;
		line-height: normal;
		word-wrap: break-word;
		background-image: linear-gradient(#6e37df, #2b186a);
		padding-bottom: 60px;

		.graphic {
			display: flex;
			max-width: 1200px;
			margin: 70px auto 0;
			padding-bottom: 85px;

			.item {
				width: 100%;
				flex: 1;
				display: flex;
				flex-direction: column;
				align-items: center;

				.image {
					margin-bottom: 45px;
				}

				.image img {
					width: 170px;
					height: 170px;
				}

				.small-title {
					margin-bottom: 15px;
					font-size: 21px;
				}

				.text {
					text-align: center;
					font-size: 16px;
					color: #dcdcdc;
				}
			}
		}

		.case {
			width: 1200px;
			min-height: 285px;
			border: 1px dashed #d4d4d4;
			margin: 0 auto;
			padding: 0 50px;
		}

		.case>h3 {
			padding-top: 40px;
			margin: 0;
			font-size: 24px;
		}

		.case>.item:not(:last-child) {
			border-bottom: 1px dashed #fff;
		}

		.case>.item {
			padding: 30px 0;
			font-size: 16px;
			line-height: 35px;
			letter-spacing: 1px;
		}

		.index-module-title {
			display: flex;
			padding: 110px 0 25px 0;
			font-size: 30px;
			line-height: normal;
			letter-spacing: 3px;
			justify-content: center;
			position: relative;
			color: #fff;
			padding-top: 90px;
		}

		.index-module-title::after {
			content: "";
			width: 85px;
			height: 3px;
			position: absolute;
			left: 0;
			right: 0;
			bottom: 0;
			margin: auto;
			background-color: #bfbfbf;
		}
	}
</style>
