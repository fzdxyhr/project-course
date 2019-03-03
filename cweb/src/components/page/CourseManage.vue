<template>
	<div class="course-manager-index">
		<div class="secction diyscrollbar">
			<v-list-top placeholder="输入课程名查找" btnText="添加课程" @addBtn="addCourse" @search="searchCourseAction"></v-list-top>
			<v-courses-list @setSearchText="setSearchText" @setCurrentNum="setCurrentNum" @setCurrentPage="setCurrentPage" :page="page"
			 :child-courses-list='coursesList' @sonDelCourse="fatherDelCourse" @childGetCourses="getCourses" @childAmendCourse="fatherAmendCourse"></v-courses-list>
		</div>
		<el-dialog :title="dialog.title" :visible.sync="dialog.control" size="small">
			<el-form ref="dialog.form" :model="dialog.form" label-width="100px">
				<el-form-item label="课程名">
					<el-input v-model="dialog.form.course_name"></el-input>
				</el-form-item>
				<el-form-item label="任课老师">
					<el-select v-model="dialog.form.teacher_id" placeholder="请选择">
						<el-option v-for="item in teachers" :key="item.value" :label="item.label" :value="item.value">
						</el-option>
					</el-select>
				</el-form-item>
				<el-form-item label="标签">
					<el-select v-model="dialog.form.tag_id" multiple placeholder="请选择">
						<el-option v-for="item in tags" :key="item.value" :label="item.label" :value="item.value">
						</el-option>
					</el-select>
				</el-form-item>
				<el-form-item label="课程教室">
					<el-input v-model="dialog.form.course_room"></el-input>
				</el-form-item>
				<el-form-item label="最大可选人数">
					<el-input v-model="dialog.form.max_student"></el-input>
				</el-form-item>
				<el-form-item label="课程简介">
					<el-input type="textarea" :rows="3" v-model="dialog.form.course_desc"></el-input>
				</el-form-item>
				<el-form-item label="课程提示">
					<el-input type="textarea" :rows="3" v-model="dialog.form.course_tip"></el-input>
				</el-form-item>
				<el-form-item label="课程图片">
					<el-upload name="editormd-image-file" class="avatar-uploader" :action="`http://localhost:8085/v1/courses/images/upload`"
					 :show-file-list="false" :on-success="handleAvatarSuccess" :before-upload="beforeAvatarUpload">
						<img v-if="showImage" :src="imageUrl" class="avatar">
						<i v-else class="el-icon-plus avatar-uploader-icon"></i>
					</el-upload>
				</el-form-item>
			</el-form>
			<span slot="footer" class="dialog-footer">
				<el-button @click="dialog.control = false">取 消</el-button>
				<el-button type="primary" @click="onSubmit">确 定</el-button>
			</span>
		</el-dialog>
	</div>
</template>

<script>
	import vCoursesList from './course/CoursesList.vue'
	import vListTop from './public/ListTop.vue'
	export default {
		name: 'CourseManage',
		components: {
			vListTop,
			vCoursesList,
		},
		data() {
			return {
				dialog: {
					title: '',
					control: false, //控制dialog的显示与关闭
					form: {
						course_name: '', //课程名
						course_desc: '', //课程简介
						course_tip: '', //课程提示
						course_image_url: '', //课程图片
						tag_id: [],
						max_student: "",
						teacher_id: "",
						course_room: ""
					},
				},
				teachers: [],
				tags: [],
				dynamicTags: ['标签一', '标签二', '标签三'],
				inputVisible: false,
				inputValue: '',
				coursesList: [],
				showImage: false,
				page: {
					currentPage: 1, //当前页
					totalCourses: 0, //总条数
					currentNum: 10, //每页多少条
					searchText: '',
				}

			}
		},
		mounted() {
			this.getCourses(this.page.currentPage, this.page.currentNum, this.page.searchText);
			this.findTags();
			this.findTeachers();
		},
		computed: {
			imageUrl() {
				return "http://localhost:8085/v1/courses/images/download?relative_path=" + this.dialog.form.course_image_url;
			}
		},
		methods: {
			setCurrentPage(num) {
				this.page.currentPage = num;
			},
			setCurrentNum(num) {
				this.page.currentNum = num;
			},
			setSearchText(keyText) {
				this.page.searchText = keyText;
			},
			findTeachers() {
				this.$axios.get("/v1/users?role=teacher&page_no=1&page_size=1000000").then((response) => {
					let message = response.data;
					message.items.forEach((item) => {
						let temp = {};
						temp.label = item.user_name;
						temp.value = item.id;
						this.teachers.push(temp);
					});
				}, (response) => {
					this.$message.error('获取老师失败');
				});
			},
			findTags() {
				this.$axios.get("/v1/tags?page_no=1&page_size=1000000").then((response) => {
					let message = response.data;
					message.items.forEach((item) => {
						let temp = {};
						temp.label = item.tag_name;
						temp.value = item.id;
						this.tags.push(temp);
					});
				}, (response) => {
					this.$message.error('获取标签失败');
				});
			},
			getCourses(page, rows, search) { //获取数据(页数，每页多少条，关键词)
				this.$axios.get("/v1/courses?key=" + search + "&page_no=" + page + "&page_size=" + rows).then((response) => {
					let message = response.data;
					this.coursesList = message.items;
					this.page.totalCourses = message.total;
					this.setCurrentPage(page);
					this.setCurrentNum(rows);
					this.setSearchText(search);
				}, (response) => {
					this.$message.error('获取课程失败');
				});
			},
			addCourse() { //显示添加的dialog
				this.dialog.form = {
					course_name: '', //课程名
					course_desc: '', //课程简介
					course_tip: '', //课程提示
					course_image_url: '', //课程图片
					tag_id: [],
					max_student: "",
					teacher_id: "",
					course_room: ""
				};
				this.dialog.control = true;
				this.dialog.title = "添加课程";
				this.showImage = false;
			},
			fatherAmendCourse(course) { //显示修改的dialog
				this.dialog.form = JSON.parse(JSON.stringify(course));
				if (this.dialog.form.tag_id) {
					let temp = this.dialog.form.tag_id.split(",");
					this.dialog.form.tag_id = [];
					temp.forEach((item) => {
						this.dialog.form.tag_id.push(parseInt(item));
					});
				}
				if (this.dialog.form.course_image_url) {
					this.showImage = true;
				}
				this.dialog.control = true;
				this.dialog.title = "修改课程";
			},
			fatherDelCourse(id) {
				this.$confirm('此操作将永久删除该课程, 是否继续?', '提示', {
					confirmButtonText: '确定',
					cancelButtonText: '取消',
					type: 'warning'
				}).then(() => {
					this.$axios.delete("/admin/course/" + id).then((response) => {
						if (response.data.data) {
							this.$message.success('删除成功');
							this.getCourses(this.page.currentPage, this.page.currentNum, this.page.searchText);
						} else {
							this.$message.error('删除失败');
						}

					}, (response) => {
						this.$message.error('链接失败');
					});
				}).catch(() => {
					this.$message({
						type: 'info',
						message: '已取消删除'
					});
				});
			},
			searchCourseAction(keyWord) { //搜索按钮
				this.page.searchText = keyWord;
				this.getCourses(this.page.currentPage, this.page.currentNum, this.page.searchText);
			},
			onSubmit() { //处理添加或修改课程
				let tempTagId = this.dialog.form.tag_id;
				this.dialog.form.tag_id = this.dialog.form.tag_id.join(",");
				if (!this.dialog.form.id) { //添加课程
					this.$axios.post("/v1/courses", this.dialog.form).then((response) => {
						if (response.status == 200) {
							this.dialog.control = false;
							this.$message.success('添加成功');
							this.getCourses(this.page.currentPage, this.page.currentNum, this.page.searchText);
						} else {
							this.$message.error('添加失败');
						}

					}, (response) => {
						this.dialog.form.tag_id = tempTagId;
						this.$message.error('链接失败');
					});
				} else { //修改课程
					this.$axios.put("/v1/courses/" + this.dialog.form.id,
						this.dialog.form
					).then((response) => {
						console.log("response=",response)
						if (response.status == 200) {
							this.dialog.control = false;
							this.$message.success('修改成功');
							this.getCourses(this.page.currentPage, this.page.currentNum, this.page.searchText);
						} else {
							this.$message.error('修改失败');
						}

					}, (response) => {
						this.$message.error('链接失败');
					});
				}

			},
			handleAvatarSuccess(file) {
				this.dialog.form.course_image_url = file;
				this.showImage = true;
			},
			beforeAvatarUpload(file) {
				const isLt2M = file.size / 1024 / 1024 < 4;
				if (!isLt2M) {
					this.$message.error('上传图片大小不能超过 4MB!');
				}
				return isLt2M;
			},
			handleClose(tag) {
				this.dynamicTags.splice(this.dynamicTags.indexOf(tag), 1);
			},
			showInput() {
				this.inputVisible = true;
				this.$nextTick(_ => {
					this.$refs.saveTagInput.$refs.input.focus();
				});
			},
			handleInputConfirm() {
				let inputValue = this.inputValue;
				if (inputValue) {
					this.dynamicTags.push(inputValue);
				}
				this.inputVisible = false;
				this.inputValue = '';
			}
		}
	}
</script>

<style lang="scss">
	.course-manager-index {
		.avatar-uploader .el-upload {
			border: 1px dashed #d9d9d9;
			border-radius: 6px;
			cursor: pointer;
			position: relative;
			overflow: hidden;
			width: 100%;
			height: 100%;
			max-width: 300px;
			max-height: 169px;
		}

		.button-new-tag {
			margin-left: 10px;
			height: 32px;
			line-height: 30px;
			padding-top: 0;
			padding-bottom: 0;
		}

		.input-new-tag {
			width: 90px;
			height: 32px;
			line-height: 29px;
			margin-left: 10px;
			vertical-align: bottom;
		}

		.avatar-uploader .el-upload:hover {
			border-color: #20a0ff;
		}

		.avatar-uploader-icon {
			font-size: 28px;
			color: #8c939d;
			width: 100%;
			height: 100%;
			line-height: 178px;
			text-align: center;
		}

		.avatar {
			width: 100%;
			height: 100%;
			max-width: 300px;
			max-height: 169px;
			display: block;
		}
	}
</style>
