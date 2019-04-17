<template>
	<div class="case-index">
		<h2 class="index-module-title">客户案例</h2>
		<div class="case-company clearfix">
			<div class="images img0" style="background-image:url(/static/img/front/21.png)"></div>
			<div class="images img1" style="background-image:url(/static/img/front/22.png)"></div>
			<div class="images img2" style="background-image:url(/static/img/front/23.png)"></div>
			<div class="images img3" style="background-image:url(/static/img/front/24.png)"></div>
			<div class="images img4" style="background-image:url(/static/img/front/25.png)"></div>
			<div class="images img5 ie8" style="background-image:url(/static/img/front/26.png)"></div>
		</div>
		<div class="to-case module-width">
			<a class="case-btn" href="/case#one" target="_blank">查看案例详情</a>
		</div>
	</div>
</template>

<script>
</script>

<style lang="scss">
	.case-index {
		color: #333;
		padding-bottom: 60px;

		.case-company {
			max-width: 1500px;
			margin: 55px auto 50px;
			display: flex;
			flex-wrap: wrap;

			.images:not(:nth-child(6)) {
				margin-right: 9px;
			}

			.images {
				width: 16%;
				height: 100px;
				background-repeat: no-repeat;
				background-position: center;
				background-size: contain;
				margin-bottom: 20px;
			}
		}

		.to-case {
			display: flex;
			justify-content: center;
			align-items: center;
			padding-bottom: 60px;
			background: #fff;

			.case-btn {
				padding: 16px 60px;
				border: 1px solid #5a2ac9;
				border-radius: 50px;
				font-size: 16px;
				color: #5a2ac9;
			}
		}

		.module-width {
			min-width: 1200px;
			display: flex;
			justify-content: center;
		}

		.index-module-title {
			display: flex;
			padding: 110px 0 25px 0;
			font-size: 30px;
			line-height: normal;
			color: #5a2ac9;
			letter-spacing: 3px;
			justify-content: center;
			position: relative;
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
