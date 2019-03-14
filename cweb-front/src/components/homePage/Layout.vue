<template>
	<div class="layout-index">
		<div class="carousel-content">
			<homePage-carousel></homePage-carousel>
		</div>
		<div class="course-content">
			<homePage-content></homePage-content>
		</div>
	</div>
</template>
<script scoped>
	//主页内容
	import Carousel from './Carousel.vue'
	import Content from './Content.vue'

	export default {
		name: 'homePageLayout',
		components: {
			'homePage-carousel': Carousel,
			'homePage-content': Content,
		},
	}
</script>
<style lang="scss">
	.layout-index {
		min-width: 960px;
		max-width: 1200px;
		margin: 20px auto;
    
    .carousel-content {}
    
    .course-content {}
	}
</style>
