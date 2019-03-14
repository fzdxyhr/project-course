<template>
	<div class="file-upload">
		<el-upload ref="upload" class="m-upload-wrapper" :action="action" :before-upload="beforeUpload" :on-success="handleSuccess"
		 :on-error="handleError" :on-remove="handleRemove" :accept="accept" :show-file-list="showFileList" :list-type="listType"
		 :file-list="fileList" multiple :limit="limit" :on-exceed="handleOnExceed">
			<el-button size="small" type="primary" icon="el-icon-upload2" class="upload-btn">{{buttonText}}</el-button>
		</el-upload>
		<!-- <div class="img-content" v-if="fileList.length > 0">
			<img :src="item.url" height="100px" width="330px" v-for="item in fileList"></img>
		</div> -->
	</div>
</template>

<script>
	export default {
		props: {
			showFileList: {
				type: Boolean,
				default () {
					return false;
				}

			},
			buttonText: {
				type: String,
				default () {
					return "上传图片";
				}
			},
			limit: {
				type: Number,
				default () {
					return 1;
				}
			},
			fileList: {
				type: Array,
				default () {
					return [];
				}
			},
			isImg: {
				type: String,
				default () {
					return "img";
				}
			},
			listType: {
				type: String,
				default () {
					return "picture";
				}
			}

		},
		data() {
			const {
				ads_id
			} = this.$route.params
			return {
				action: this.$store.state.host +`/v1/files/upload`,
				accept: '.mp4,.doc,.docx,.ppt,.pptx,.pdf'
			}
		},
		methods: {
			beforeUpload(file) {
				if (this.isImg === 'img') {
					const isImg = file.type === 'image/jpeg' ||
						file.type === 'image/jpg' ||
						file.type === 'image/png' ||
						file.type === 'image/gif' ||
						file.type === 'image/bmp' ||
						file.type === 'image/svg+xml';
					if (!isImg) {
						this.$notify.error('上传文件只能是 JPG, JPEG, PNG, GIF, BMP!')
					}
					const isLimitSize = file.size / 1024 / 1024 < 10
					if (!isLimitSize) {
						this.$notify.error('上传图片大小不能超过 10MB,请压缩后再传!')
					}
					return isImg && isLimitSize
				}
				if (this.isImg === 'file') {
					const isImg = /\.mp4$/.test(file.name) || /\.doc$/.test(file.name) || /\.docx$/.test(file.name) ||
						/\.ppt$/.test(file.name) || /\.pptx$/.test(file.name) || /\.pdf$/.test(file.name)
					if (!isImg) {
						this.$notify.error({
							message: '上传文件只能是 mp4, doc, docx, ppt, pptx, pdf!'
						});
					}
					const isLimitSize = file.size / 1024 / 1024 < 50
					if (!isLimitSize) {
						this.$notify.error('上传文件大小不能超过 50MB!')
					}
					return isImg && isLimitSize
				}
				if (this.isImg === 'vedio') {
					const isImg = /\.mp4$/.test(file.name)
					if (!isImg) {
						this.$notify.error({
							message: '请选择MP4视频'
						});
					}
					const isLimitSize = file.size / 1024 / 1024 < 30
					if (!isLimitSize) {
						this.$notify.error('MP4视频大小不能超过 30MB!')
					}
					return isImg && isLimitSize
				}
			},
			handleSuccess(res, file) {
				let tempImg = {};
				tempImg.name = file.name;
				tempImg.url = res;
				if (this.limit === 2) {
					this.fileList.splice(0, 1, tempImg)
				}
        console.log("success=",res)
				this.$emit('change', res)
			},
			handleRemove(file, fileList) {
				this.fileList.forEach((item, index) => {
					if (item.name === file.name) {
						this.fileList.splice(index, 1);
					}
				});
				this.$emit('change', "")
			},
			handleError(err, file) {
				console.log(err)
				const self = this
				try {
					var reader = new FileReader()
					reader.onload = function() {
						self.$emit('change', this.result)
					}
					reader.readAsDataURL(file.raw)
				} catch (e) {
					console.log('readAsDataURL', e)
					this.$emit('change', '')
				}
				//         if (this.limit === 1) {
				//         	this.$refs.upload.clearFiles()
				//           this.fileList.splice(0)
				//         }
			},
			handleOnExceed(files, fileList) {
				console.log(files, fileList)
				this.$refs.upload.clearFiles()
				this.fileList.splice(0)
			}
		}
	}
</script>

<style lang="scss">
	.file-upload {
		margin-top: 10px;
		margin-bottom: 5px;

		.m-upload-wrapper {
			display: inline-block;
		}

		.img-content {
			margin-top: 10px;
		}

		.el-upload-list {
			width: 350px;
		}
	}
</style>
