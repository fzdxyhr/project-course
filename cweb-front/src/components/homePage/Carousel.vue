<template>
	<div class="carousel-index">
		<el-carousel :interval="4000" type="card" height="350px">
			<el-carousel-item v-for="item in images">
				<img :src="item.course_image_url" @click="go2CourseDetail(item.id)"/>
			</el-carousel-item>
		</el-carousel>
	</div>
</template>
<script>
	//走马灯
	export default {
		name: 'carousel',
		components: {
		},
		data: () => {
// 			let imgs = [
// 				"../../../../static/img/carousel.jpg",
// 				"../../../../static/img/carousel.jpg",
// 				"../../../../static/img/carousel.jpg"
// 			];
			return {
				images:[]
			};
		},
		mounted(){
			this.findCarousels();
		},
		methods: {
			findCarousels() {
				this.$axios.get('/v1/courses/recommend').then((response) => {
					this.images = response.data;
				}, (response) => {
					this.$message.error('获取轮播信息');
				});
			},
			go2CourseDetail(courseId) {
				this.$router.push({
					name: "courseDetail",
					query: {
						courseId: courseId
					}
				})
			}
		},
	}
</script>
<style lang="scss">
  .carousel-index {
    .el-carousel__item:nth-child(2n) {
    		background-color: #99a9bf;
    	}
    
    	.el-carousel__item:nth-child(2n+1) {
    		background-color: #d3dce6;
    	}
    
    	.el-carousel__item img {
    		height: 100%;
    		width: 100%;
    	}
  }
</style>
