<template>
  <div>
    <div class="row justify-content-center">
      <div class="col-xl-10 col-lg-12 col-md-9">
        <div class="card mt-5">
          <div class="card-body p-0">
            <div class="row">
              <!-- TODO: 이미지(좌) -->
              <div class="col-lg-6">
                <!-- <img
                  src="@/assets/images/puppy-1920_1280_2.jpg"
                  class="img-fluid"
                /> -->
              </div>
              <!-- TODO: 입력양식(우) -->
              <div class="col-lg-6">
                <div class="p-5">
                  <div class="text-center">
                    <h1 class="h4 mb-4">Welcome!</h1>
                  </div>
                  <div class="user">
                    <div class="form-group">
                      <input
                        type="text"
                        class="form-control form-control-user mb-3"
                        placeholder="이메일을 넣기"
                        v-model="user.email"
                      />
                    </div>
                    <div class="form-group">
                      <input
                        type="password"
                        class="form-control form-control-user mb-3"
                        placeholder="패스워드 넣기"
                        v-model="user.password"
                      />
                    </div>
                    <button
                      class="btn btn-primary btn-user w-100 mb-3"
                      @click="login"
                    >
                      Login
                    </button>
                    <!-- 에러 메시지 표시 -->
                    <div
                      v-if="errorMessage"
                      class="alert alert-danger text-center mt-3"
                      role="alert"
                      v-html="errorMessage"
                    ></div>
                  </div>
                  <hr />
                  <div class="text-center">
                    <div class="d-flex justify-content-center">
                      <a class="small mx-2" href="/register">회원가입</a>
                      <span>|</span>
                      <a class="small mx-2" href="#">아이디 찾기</a>
                      <span>|</span>
                      <a class="small mx-2" href="#">비밀번호 찾기</a>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import MemberService from "@/services/auth/MemberService";

export default {
  data() {
    return {
      user: {
        email: "",
        password: "",
      },
      
      errorMessage: "", // 에러 메시지 상태 관리
    };
  },
  methods: {
    async login() {
      try {
        let response = await MemberService.login(this.user);
        console.log(response.data);

        this.$store.state.loggedIn = true;

        localStorage.setItem("user", JSON.stringify(response.data));

        this.errorMessage = ""; // 에러 메시지 초기화

        this.$router.push("/");
      } catch (error) {
        this.$store.state.loggedIn = false;

        this.errorMessage = `
          로그인에 실패했습니다.<br />
          아이디 또는 비밀번호를 맞게 입력해 주세요.
        `;

        console.log(error);
      }
    },
  },
  mounted() {
    if (this.$store.state.loggedIn === true) {
      this.$router.push("/");
    }
  },
};
</script>

<style>
.alert {
  font-size: 0.9rem;
  border-radius: 5px;
  padding: 10px 15px;
}
.alert-danger {
  background-color: #f8d7da;
  color: #842029;
  border: 1px solid #f5c2c7;
}
.alert .bi {
  font-size: 1.2rem;
}
</style>
