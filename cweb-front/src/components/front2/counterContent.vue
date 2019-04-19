<template>
	<div class="counter-index">
		<div id="fh5co-counter" class="fh5co-counters animated">
			<div class="container">
				<div class="row">
					<div class="col-md-3 text-center animate-box fadeInUp animated-fast" style="margin-bottom: 42px;">
						<span class="fh5co-counter">快速高效</span>
						<span class="fh5co-counter-label">基于代码生成，让软件开发极限提速</span>
					</div>
					<div class="col-md-3 text-center animate-box fadeInUp animated-fast" style="margin-bottom: 42px;">
						<span class="fh5co-counter">安全稳定</span>
						<span class="fh5co-counter-label">完善的安全体系，多重安全验证</span>
					</div>
					<div class="col-md-3 text-center animate-box fadeInUp animated-fast" style="margin-bottom: 42px;">
						<span class="fh5co-counter">完美兼容</span>
						<span class="fh5co-counter-label">响应式设计风格，兼容各种终端</span>
					</div>
					<div class="col-md-3 text-center animate-box fadeInUp animated-fast" style="margin-bottom: 42px;">
						<span class="fh5co-counter">支持SAAS</span>
						<span class="fh5co-counter-label">轻松搭建云平台</span>
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
	.counter-index {
		#fh5co-counter {
			padding: 5em 0;
			clear: both;
			background-size: cover;
			background-position: top center;
			background-repeat: no-repeat;
			position: relative;
		}

		.fh5co-counters {
			padding: 5em 0;
			background-size: cover;
			background-attachment: fixed;
			background-position: center center;

			.container {
				width: 1200px;
				margin: 0 auto;
				padding: 0;
			}

			.row {
				margin-left: -15px;
				margin-right: -15px;
			}

			.col-md-3 {
				width: 22%;
			}
        
			.col-md-3 {
				position: relative;
				min-height: 1px;
				padding-left: 15px;
				padding-right: 15px;
				float: left;
			}

			.text-center {
				text-align: center;
			}

			.fadeInUp {
				-webkit-animation-name: fadeInUp;
				animation-name: fadeInUp;
			}

			.animated-fast {
				-webkit-animation-duration: .5s;
				animation-duration: .5s;
				-webkit-animation-fill-mode: both;
				animation-fill-mode: both;
			}

			.fh5co-counter {
				font-size: 44px;
				display: block;
				color: rgba(0, 0, 0, 0.7);
				font-family: "Work Sans", Arial, sans-serif;
				width: 100%;
				font-weight: 400;
				margin-bottom: .3em;
			}

			.fh5co-counter-label {
				color: rgba(0, 0, 0, 0.5);
				text-transform: uppercase;
				font-size: 14px;
				letter-spacing: 5px;
				margin-bottom: 2em;
				display: block;
			}
		}

		.animated {
			-webkit-animation-duration: 1s;
			animation-duration: 1s;
			-webkit-animation-fill-mode: both;
			animation-fill-mode: both;
		}

		.js .animate-box {
			opacity: 0;
		}

	}
</style>
