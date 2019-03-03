<template>
	<div class="add-chapter-index">
		<el-form label-width="80px" :model="form" ref="form">
			<el-form-item label="章节名称">
				<el-input v-model="form.chapter_name"></el-input>
			</el-form-item>
			<el-form-item label="章节描述">
				<el-input type="textarea" :rows="4" v-model="form.chapter_desc"></el-input>
			</el-form-item>
		</el-form>
		<div>对象信息</div>
		<el-form label-width="90px" :inline="true" :model="form" ref="childForm">
			<div class="child-chapter-content" v-for="item in form.childChapters">
				<el-form-item label="子章节标题">
					<el-input v-model="item.chapter_name"></el-input>
				</el-form-item>
				<el-form-item label="子章节内容">
					<fileUpload isImg="file" @change="fileChange(item)" buttonText="上传文件" :showFileList="true" :limit="2" :fileList="fileList"></fileUpload>
				</el-form-item>
			</div>
		</el-form>
		<div class="dialog-footer">
			<el-button @click="doCancel">取 消</el-button>
			<el-button @click="onSubmit" type="primary">确 定</el-button>
		</div>
	</div>
</template>

<script>
	import fileUpload from '@components/common/file-upload.vue';

	export default {
		components: {
			fileUpload
		},
		data() {
			return {
				fileList: [],
				form: {
					chapter_name: "",
					chapter_desc: "",
					childChapters: [{
						chapter_name: "",
						chapter_parent_id: "",
						chapter_type: "",
						chapter_file_path: ""
					}]
				}
			}
		},
		methods: {
			onSubmit() {

			},
			doCancel() {

			},
			fileChange(data, res) {
				console.log("data=", data, "res=", res)
			}
		},
	}
</script>

<style lang="scss">
	.add-chapter-index {
		.dialog-footer {
			text-align: right;
		}

		.child-chapter {
			.el-input__inner {
				width: 250px;
			}
		}
	}
</style>
