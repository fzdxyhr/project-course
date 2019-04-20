<template>
	<div class="attach-package-index">
		<headerContent menu="attach"></headerContent>
		<div class="attach-content">
			<el-row :gutter="20">
				<el-col :xs="12" :sm="8" :md="8" :lg="8" :xl="8" style="width: 50%;">
					<img class="img-responsive" src="/static/img/front2/work_2.png" alt="work">
				</el-col>
				<el-col :xs="12" :sm="16" :md="16" :lg="16" :xl="16" style="width: 50%;">
					<div class="right">
						<div>
							<h3>关于我们</h3>
							<p>JavaFast是中国优秀的Java快速开发平台软件，集合基础架构、代码生成、报表配置等功能，致力于"让企业项目开发更简单高效". </p>
						</div>
						<div style="padding-top: 60px;">
							<h3>企业愿景</h3>
							<p>企业在项目开发的过程中，需要要写很多很多的代码，但仔细的分析来看，很多代码基本上都是重复的编写，功能大同小异。 </p>
							<br>
							<p>快速开发平台作为一种新型的开发工具，它的出现减少了代码编写量，简化了开发过程，缩短了开发周期，提高了开发效率，节省了开发成本。越来越多的开发者使用快速开发平台来完成自己的开发工作。 </p>
						</div>
					</div>
				</el-col>
			</el-row>
		</div>
		<div class="attach-content">
			<el-row :gutter="20">
				<el-col :xs="12" :sm="8" :md="8" :lg="8" :xl="8" style="width: 50%;">
					<div class="right">
						<div>
							<h3>联系我们</h3>
							<div class="attach-info">
								<i class="course-icon-location" style="font-size: 24px;"></i>
								<p style="display: inline-block;margin-left: 10px;">
									深圳市龙岗区龙岗大道8288号
									深圳大运软件小镇39栋二楼
								</p>
							</div>
							<div class="attach-info">
								<i class="course-icon-phone" style="font-size: 23px;"></i>
								<p style="display: inline-block;margin-left: 10px;">
									0755-84823400
								</p>
							</div>
							<div class="attach-info">
								<i class="course-icon-message" style="font-size: 27px;"></i>
								<p style="display: inline-block;margin-left: 10px;">
									javafast@qikucrm.com
								</p>
							</div>
							<div class="attach-info">
								<i class="course-icon-network" style="font-size: 29px;"></i>
								<p style="display: inline-block;margin-left: 10px;">
									www.javafast.cn
								</p>
							</div>
						</div>
					</div>
				</el-col>
				<el-col :xs="12" :sm="16" :md="16" :lg="16" :xl="16" style="width: 50%;">
					<img class="img-responsive" src="/static/img/front2/work_2.png" alt="work">
				</el-col>
			</el-row>
		</div>
		<div style="clear: both;padding-bottom: 40px;"></div>
		<footerContent></footerContent>
	</div>
</template>

<script>
	import headerContent from './headerContent.vue'
	import footerContent from './footerContent.vue'

	export default {
		components: {
			headerContent,
			footerContent
		},
		data() {
			return {

			};
		}
	}
</script>

<style lang="scss">
	.attach-package-index {
		.attach-content {
			width: 1200px;
			margin: 0 auto;
			clear: both;
			padding-top: 100px;

			.img-responsive {
				display: block;
				max-width: 100%;
				height: auto;
				// padding-left: 10%;
			}

			.right {
				padding: 30px 30px 30px 30px;
				margin-bottom: 30px;
				font-family: "Microsoft Yahei", "HanHei SC", PingHei, "PingFang SC", "Helvetica Neue", Helvetica, Arial, "Hiragino Sans GB", "Heiti SC", "WenQuanYi Micro Hei", sans-serif;
				font-weight: 400;
				color: #333;
				background: #fff;

				h3 {
					font-size: 20px;
					margin-bottom: 20px;
					line-height: 1.5;
				}

				p {
					font-size: 14px;
					line-height: 1.7;
				}
				.attach-info {
					margin-bottom: 30px;
					
					i {
						color: #EA9215;
						font-size: 25px;
					}
				}
			}
		}
	}
</style>
