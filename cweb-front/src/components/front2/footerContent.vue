<template>
	<div class="index-footer">
		<div class="divImage" style="background-image:url(/static/img/front2/img_bg_1.jpg)">
			<div class="overlay"></div>
			<div class="image-title">
				<div style="font-size: 32px;">开启智能快速开发的新时代</div>
				<p>降低企业人力成本和项目风险，从繁琐的代码中解脱出来，让工程师更加专注于业务.</p>
				<el-button class="image-title-button">咨询</el-button>
			</div>
		</div>
		<div class="footerDiv">
			<ul class="foot_ul">
				<li class="li_A" style="margin: 60px 0 20px 0;">
					<img src="/static/img/front/logoRGB.png" style="max-height: 60px;width:170px">
				</li>
				<li class="li_A" style="width:290px;">
					<div style="margin-top:7px;font-size:16px;color:#000">电话咨询：4008-400-510</div>
				</li>
			</ul>
			<ul class="foot_ul foot_ul_small" style="min-width:120px">
				<li class="li_A footer_badge_hover clickPath" alt="about">
					关于我们
				</li>
				<li class="li_A footer_badge_hover clickPath" alt="/about#us">
					联系我们
				</li>
				<li class="li_A footer_badge_hover clickPath" alt="join">
					加入我们
				</li>
				<li class="li_A footer_badge_hover clickPath" alt="cooperatives">
					合作伙伴
				</li>
			</ul>
			<ul class="foot_ul foot_ul_small" style="min-width:120px;margin-right:15px">
				<li class="li_A footer_badge_hover clickPath" alt="media">
					媒体资讯
				</li>
				<li class="li_A footer_badge_hover clickPath" alt="download">
					下载中心
				</li>
				<li class="li_A footer_badge_hover clickPath" alt="activity/bucketCo?pc_bottom=26">
					全家桶活动
				</li>
			</ul>
			<ul class="foot_ul foot_ul_small">
				<li class="li_A text-center li_center">
					<img src="/static/img/front/weixin.png" style="width:110px;height: 110px">
				</li>
				<li class="li_A text-center li_center">
					关注搭搭云公众号
				</li>
			</ul>
			<ul class="foot_ul foot_ul_small">
				<li class="li_A text-center li_center">
					<img src="/static/img/front/weibo.png" style="width:110px;height: 110px">
				</li>
				<li class="li_A text-center li_center">
					关注搭搭云微博
				</li>
			</ul>
		</div>
		<div class="footerRow footerRight text-center">
			<span class="font_size_14">
				2009-2018 © 广州市九章信息科技有限公司 粤ICP备11042480号-4
			</span>
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
	.index-footer {
		background: #F5F5F5;
		color: #303030;
		min-width: 1200px;
		height: 250px;
		letter-spacing: 0px;

		.divImage {
			height: 420px;
			width: 100%;
			position: relative;

			.overlay {
				z-index: 1;
				position: absolute;
				bottom: 0;
				top: 0;
				left: 0;
				right: 0;
				background: rgba(0, 0, 0, 0.7);
			}

			.image-title {
				z-index: 100;
				position: absolute;
				top: 100px;
				color: white;
				line-height: 1.5;
				text-align: center;
				left: 0;
				right: 0;

				p {
					margin-top: 25px;
					color: rgba(255, 255, 255, 0.5);
					font-size: 18px;
					line-height: 1.5;
				}

				.image-title-button {
					background: #EA9215;
					border-color: #EA9215;
          height: 50px;
          color: white;
          font-weight: 400;
          margin-top: 4%;
          width: 120px;
          border-radius: 30px;
				}
			}
		}

		.footerDiv {
			height: 210px;
			display: flex;
			justify-content: center;
			width: 1200px;
			margin-left: auto;
			margin-right: auto;
		}

		.foot_ul {
			min-width: 180px;
			margin: 10px 20px 0px 20px;
			float: left;

			.li_A {
				margin: 20px 0px;
				line-height: 24px;
				font-size: 14px;
			}

			li {
				list-style-type: none;
			}

			.li_center {
				text-align: center;
			}
		}

		.footer_badge_hover {
			text-align: center;
		}

		.foot_ul_small {
			margin: 10px 0px 0px 0px;
			min-width: 160px;
		}

		.text-center {
			text-align: center;
		}

		.footerRow {
			min-width: 1200px;
			clear: both;
			color: #CCC;
			margin-left: auto;
			margin-right: auto;
		}

		.footerRight {
			background: #333;
			height: 40px;
			line-height: 40px;
		}

		.font_size_14 {
			font-size: 14px;
		}
	}
</style>
