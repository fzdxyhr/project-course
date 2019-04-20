<template>
	<div class="question-package-index">
		<headerContent menu="question"></headerContent>
		<div style="clear: both;"></div>
		<div class="question-content">
			<div class="question-chapter-content">
				<div style="margin-bottom: 30px;">
					<h3>怎么购买软件？</h3>
					<p>
						支持在线下单购买，也可以联系在线客服.
					</p>
				</div>
				<div style="margin-bottom: 30px;">
					<h3>JavaFast有哪些服务？</h3>
					<p>
						购买后可以获取您所购买的所有源码.
					</p>
				</div>
				<div style="margin-bottom: 30px;">
					<h3>是否提供发票？</h3>
					<p>
						我们提供正规的增值税专用发票和增值税普通发票，并会按照您提供的地址快递至您提供的地址.
					</p>
				</div>
				<div style="margin-bottom: 30px;">
					<h3>需要提供签订合同吗？</h3>
					<p>
						我们会提供完整的纸质版合同邮寄给您.
					</p>
				</div>
				<div style="margin-bottom: 30px;">
					<h3>如何付款？</h3>
					<p>
						一般采用企业对公付款方式，也可以支付宝、微信.
					</p>
				</div>
				<div style="margin-bottom: 30px;">
					<h3>可以使用多长时间？</h3>
					<p>
						永久.
					</p>
				</div>
				<div style="margin-bottom: 30px;">
					<h3>购买后可以升级吗？</h3>
					<p>
						可以，提供免费升级服务，您可以随时升级功能模块或者最新版。您也可以在不同产品中使用不同版本.
					</p>
				</div>
				<div style="margin-bottom: 30px;">
					<h3>购买后我可以获得什么？</h3>
					<p>
						所有源码(未压缩、带注释版本)；说明文档；终身免费升级服务；必要的技术支持.
					</p>
				</div>
				<div style="margin-bottom: 30px;">
					<h3>提供相关技术服务吗？</h3>
					<p>
						提供，我们提供了为您提供了完善的使用文档，并在服务期内提供免费的QQ咨询、远程服务等.
					</p>
				</div>
				<div style="margin-bottom: 30px;">
					<h3>JavaFast兼容手机端吗？</h3>
					<p>
						兼容，JavaFast采用bootstarp响应式布局，兼容手机端访问.
					</p>
				</div>
				<div style="margin-bottom: 30px;">
					<h3>可以定制吗？</h3>
					<p>
						可以，如果您有定制的需求，请联系我们的客服人员.
					</p>
				</div>
				
			</div>
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

			};
		}
	}
</script>

<style lang="scss">
	.question-package-index {
		.question-content {
			width: 1200px;
			margin: 0 auto;
			clear: both;
			padding-top: 100px;

			.question-chapter-content {
				padding: 30px;
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
			}
		}
	}
</style>
