<template>
	<div class="benefit-index">
		<h2 class="index-module-title">使用搭搭云的好处</h2>
		<div class="graphic clearfix">
			<el-row :gutter="60" style="margin-right: 0;">
				<el-col :span="6">
					<div class="item">
						<div class="image" style="background-image:url(/static/img/front/17.png)"></div>
						<div class="text">功能模块之间数据
							<b>天然互通</b> ，杜绝信息孤岛，减少碎片化采购</div>
					</div>
				</el-col>
				<el-col :span="6">
					<div class="item">
						<div class="image" style="background-image:url(/static/img/front/18.png)"></div>
						<div class="text ie9">在线定制，在线交付，在线使用，开发周期
							<b>缩短50%</b>
						</div>
					</div>
				</el-col>
				<el-col :span="6">
					<div class="item">
						<div class="image" style="background-image:url(/static/img/front/19.png)"></div>
						<div class="text">大幅降低定制技术门槛，整体成本
							<b>降低至少40%</b>
						</div>
					</div>
				</el-col>
				<el-col :span="6">
					<div class="item ie8">
						<div class="image" style="background-image:url(/static/img/front/20.png)"></div>
						<div class="text">高度灵活，80%的功能调整都能在
							<b>1小时之内</b> 完成，并同时在PC和移动端投入使用</div>
					</div>
				</el-col>
			</el-row>
		</div>
		<div class="case">
			<h3>案例：</h3>
			<div class="item">广州某信息科技有限公司，研发和业务的复杂性需要使用软件来进行研发、销售和生产管理，但因为是创业初期，无法在IT系统上投入大量资金，经多方考量后，采用了搭搭云平台，只投入不到3万元并接受了培训，自己DIY先定制了研发管理系统，后期可以在同一账户内无缝升级，既避免了从多家公司采购不同系统带来的麻烦，又通过DIY节省了大量后期软件升级的费用。</div>
			<div class="item">深圳某医疗设备销售公司，一直在使用很多年前购买的4个分立的软件系统来管理销售、人事、仓储和财务，随着市场变化，这些软件系统越来越不能适应业务需求，全面切换至搭搭云平台后，只花了2个月快速定制了原先习惯的所有软件功能，一个账户就能使用所有软件模块，所有的数据可以受控互相引用，还可以随时根据业务变化调整软件功能。IT部门的工作量缩减为原先的20%，效率大幅提升，还有了自主能力。不但在软件采购上节省了至少几十万的费用，每年还能节省几十万后期维护费用。</div>
			<div class="item">武汉某软件开发公司，常年承接企业软件项目，一直采取传统的软件开发模式，团队有近120名程序员。新承接的项目采用搭搭云平台进行开发和交付后，一步到位有了为客户提供IT系统上云服务的能力，项目报价比原先降低了20%，增强了竞争力，同时还因为工作量大幅降低，投入的人员成本降低了60%，交付周期缩短了30%，项目利润反倒大幅提升，接单能力和对客户需求变更的应对能力也明显提高。</div>
		</div>
	</div>
</template>

<script>
</script>

<style lang="scss">
	.benefit-index {
		color: #333;
		padding-bottom: 60px;

		.graphic {
			display: flex;
			// justify-content: center;
			max-width: 1200px;
			margin: 55px auto 50px;

			.item {
				width: 100%;
				min-height: 266px;
				display: flex;
				justify-content: center;
				flex: 1;
				position: relative;
				padding: 0 17px 90px;
				background: #e8f0f9;

				.image {
					width: 80px;
					height: 80px;
					background-repeat: no-repeat;
					position: absolute;
					top: 70px;
					left: 0;
					right: 0;
					margin: auto;
				}

				.text {
					letter-spacing: 1px;
					font-size: 16px;
					margin-top: 200px;

					b {
						font-weight: bold;
						font-size: 18px;
					}
				}
			}
		}

		.case {
			width: 1100px;
			min-height: 285px;
			margin: 0 auto;
			padding: 0 50px;
			background: #e8f0f9;

			.item {
				padding: 30px 0;
				font-size: 16px;
				line-height: 35px;
				letter-spacing: 1px;
			}

			h3 {
				padding-top: 40px;
				margin: 0;
				font-size: 24px;
			}
		}

		.case>.item:not(:last-child) {
			border-bottom: 1px dashed #999;
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
