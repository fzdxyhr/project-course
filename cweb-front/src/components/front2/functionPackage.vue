<template>
	<div class="function-package-index">
		<headerContent menu="function"></headerContent>
		<div class="function-content">
			<el-row :gutter="20">
				<el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6">
					<div class="function-module-content">
						<h2>企业标准版</h2>
						<div class="price">
							<sup class="currency"></sup>
							$20000
							<small>/升级免费</small>
						</div>
						<div v-for="(item,index) in labels" :class="index % 2 ==0 ? 'item-info item-info-active':'item-info '">
							{{item.label}}
						</div>
					</div>
				</el-col>
				<el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6">
					<div class="function-module-content" >
						<h2>企业高级版</h2>
						<div class="price">
							<sup class="currency"></sup>
							$50000
							<small>/升级免费</small>
						</div>
						<div v-for="(item,index) in labels" :class="index % 2 ==0 ? 'item-info item-info-active':'item-info '">
							{{item.label}}
						</div>
					</div>
				</el-col>
				<el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6">
					<div class="function-module-content">
						<h2>企业定制版</h2>
						<div class="price">
							<sup class="currency"></sup>
							$50000+
							<small>/按需评估</small>
						</div>
						<div v-for="(item,index) in labels" :class="index % 2 ==0 ? 'item-info item-info-active':'item-info '">
							{{item.label}}
						</div>
					</div>
				</el-col>
				<el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6">
					<div class="function-module-content">
						<h2>项目合作</h2>
						<div class="price">
							<sup class="currency"></sup>
							$咨询
							<small>/按需评估</small>
						</div>
						<div v-for="(item,index) in labels" :class="index % 2 ==0 ? 'item-info item-info-active':'item-info '">
							{{item.label}}
						</div>
					</div>
				</el-col>
			</el-row>
		</div>
		<div style="clear: both;"></div>
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
				labels: [{
						"label": "基础架构"
					},
					{
						"label": "权限管理"
					},
					{
						"label": "开发组件"
					},
					{
						"label": "代码生成"
					},
					{
						"label": "报表配置"
					},
					{
						"label": "UI标签"
					},
					{
						"label": "校验框架"
					},
					{
						"label": "H+ UI"
					},
					{
						"label": "生成示例"
					},
					{
						"label": "办公模块"
					}
				]
			};
		}
	}
</script>

<style lang="scss">
	.function-package-index {
		.function-content {
			width: 1200px;
			margin: 0 auto;
			padding-top: 60px;
			clear: both;

			.function-module-content {
				width: 78%;
				text-align: center;
				padding: 30px;
				background: rgba(0, 0, 0, 0.05);
				-webkit-border-radius: 5px;
				-moz-border-radius: 5px;
				-ms-border-radius: 5px;
				border-radius: 5px;
				margin-bottom: 40px;

				.item-info {
					margin: 0;
					font-size: 16px;
					padding: 12px 10px;
				}
				
				.item-info-active {
					background: rgba(0, 0, 0, 0.04);
				}
				    
				h2 {
					margin: 0 0 50px 0;
					padding: 0;
					font-size: 23px;
					letter-spacing: 2px;
					text-transform: uppercase;
					font-weight: 700;
					color: #888f94;
				}

				.price {
					font-size: 32px;
					color: #000;
					line-height: 30px;
				}

				.price .currency {
					font-size: 30px;
					padding-right: 10px;
				}

				.price small {
					font-size: 13px;
					display: block;
					text-transform: uppercase;
					color: #888f94;
				}

				sup {
					top: -0.5em;
				}
			}
		}
	}
</style>
