<template>
	<div class="class-student-index">
		<div class="body-content" v-for="item in studentList">
			<el-card>
				<div style="padding-top: 15px;padding-bottom: 5px;">
					<span>{{item.user_name}}</span>
          <span style="margin-left: 23px;">{{item.account}}</span>
				</div>
			</el-card>
      <div v-if="item.sign === 1" class="sign-content"><i class="el-icon-check" style="margin-right: 5px;"></i>已签到</div>
      <div v-if="item.sign === 0" class="not-sign-content"><i class="el-icon-close" style="margin-right: 5px;"></i>未签到</div>
		</div>
	</div>
</template>

<script>
	export default {
		data() {
			return {
        studentList:[
          {
            user_name:"ssss",
            account:"123456789",
            sign:1
          },
          {
          	user_name:"dddd",
          	account:"1111111",
          	sign:0
          },
          {
          	user_name:"ssss",
          	account:"123456789",
          	sign:1
          },
          {
          	user_name:"dddd",
          	account:"1111111",
          	sign:0
          }
        ]
			};
		}
	}
</script>

<style lang="scss">
	.class-student-index {
    .body-content {
      position: relative;
      width: 185px;
      font-size: 16px;
      display: inline-block;
      margin-left: 15px;
      margin-top: 10px;
    }
    .sign-content {
      // transform:rotate(10deg);
      width: 75px;
      height: 20px;
      color: white;
      background-color: #67c23a;
      position: absolute;
      right: 0;
      top: 0;
    }
    .not-sign-content {
    	width: 75px;
    	height: 20px;
    	color: white;
    	background-color: #e6a23c;
    	position: absolute;
    	right: 0;
    	top: 0;
    }
  }
</style>
