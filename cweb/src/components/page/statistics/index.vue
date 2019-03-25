<template>
	<div class="statistic-index diyscrollbar">
		<div class="sign-statistic-content">
			<signStatistic></signStatistic>
		</div>
		<div class="homework-statistic-content">
			<homeworkStatistic></homeworkStatistic>
		</div>
		<div class="course-statistic-content">
			<courseStatistic></courseStatistic>
		</div>
	</div>
</template>

<script>
	import signStatistic from '@components/page/statistics/signStatistic.vue'
	import homeworkStatistic from '@components/page/statistics/homeworkStatistic.vue'
	import courseStatistic from '@components/page/statistics/courseStatistic.vue'

	export default {
		components: {
			signStatistic,
			homeworkStatistic,
			courseStatistic
		},
		data() {
			return {

			}
		},
		mounted() {},
		methods: {

		}

	}
</script>

<style lang="scss">
	.statistic-index {
		padding: 10px;
		overflow: auto;

		.sign-statistic-content {
      // height: 200px;
    }

		.homework-statistic-content {
			margin-top: 15px;
		}

		.course-statistic-content {
			margin-top: 15px;
      margin-bottom: 22px;
		}
	}
</style>
