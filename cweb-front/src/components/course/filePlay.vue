<template>
	<div class="file-play-index">
		<div v-if="resourseType == 'file'" class="left-content">
			<iframe :src="`https://view.officeapps.live.com/op/view.aspx?src=`+filePath" width='100%' height='100%' frameborder='1'>
			</iframe>
		</div>
		<div v-if="resourseType == 'vedio'" class="left-content">
			<video height="100%" width="100%" :src="filePath" controls="controls" autoplay preload="auto">
			</video>
		</div>
		<!-- <div class="right-content">
			1111111111111111
		</div> -->
	</div>
</template>

<script>
	export default {
		// 		props: {
		// 			resourseType: {
		// 				type: String,
		// 				default: "vedio"
		// 			},
		// 		},
		data() {
			return {
				resourseType: '',
				vedioPath: "http://www.w3school.com.cn/i/movie.ogg",
				filePath: "http://storage.xuetangx.com/public_assets/xuetangx/PDF/1.xls"
			}
		},
		mounted() {
			this.resourseType = this.$route.query.type;
			this.filePath = decodeURIComponent(this.$route.query.path);
		}
	}
</script>

<style lang="scss">
	.file-play-index {
		margin-top: 5px;
		background: #292929;

		.left-content {
			width: 100%;
			height: 90%;
			float: left;
		}

		.right-content {
			width: 29%;
			height: 100%;
			float: right;
		}

		width: 100%;
		height: 100%;
	}
</style>
