// 회원가입
<template>
  <div>
    <div class="row justify-content-center">
      <div class="col-xl-10 col-lg-12 col-md-9">
        <div class="card mt-5">
          <div class="card-body p-0">
            <div class="row">
              <!-- TODO: 왼쪽: 이미지(강아지그림) -->
              <div class="col-lg-6">
                <!-- <img
                  src="@/assets/images/puppy-1920_1280.jpg"
                  class="img-fluid"
                /> -->
                <!-- <img :src="require('@/assets/images/puppy-1920_1280.jpg')" class="img-fluid"/> -->
              </div>
              <!-- TODO: 오른쪽: email/password 입력양식  -->
              <div class="col-lg-6">
                <div class="p-5">
                  <div class="text-center">
                    <h1 class="h4 text-gray-900 mb-4">회원 가입</h1>
                  </div>
                  <div class="user">
                    <div class="form-group">
                      <input
                        type="email"
                        class="form-control form-control-user mb-3"
                        placeholder="이메일 입력"
                        v-model="user.email"
                      />
                    </div>
                    <div class="form-group">
                      <input
                        type="password"
                        class="form-control form-control-user mb-3"
                        placeholder="패스워드 입력"
                        v-model="user.password"
                      />
                      <div class="form-group">
                        <input
                          type="text"
                          class="form-control form-control-user mb-3"
                          placeholder="이름 입력"
                          v-model="user.name"
                        />
                      </div>
                    </div>
                    <button
                      class="btn btn-primary btn-user w-100 mb-3"
                      @click="register"
                    >
                      등록
                    </button>
                    <!-- 에러 메시지 표시 -->
                    <div
                      v-if="errorMessage"
                      class="alert alert-danger text-center"
                      role="alert"
                      v-html="errorMessage"
                    ></div>
                  </div>
                  <hr />
                  <div class="text-center">
                    <a href="/login">
                      이미 계정이 있으신가요? 로그인 하세요!
                    </a>
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
        name: "",
        codeName: "ROLE_USER",
      },

      errorMessage: "", // 에러 메시지 상태 관리
    };
  },
  methods: {
    async register() {
      try {
        let response = await MemberService.insert(this.user);
        console.log(response.data); 

        alert("회원가입이 되었습니다.");
        this.$router.push("/login");
      } catch (error) {
        this.errorMessage = `
          이미 가입된 이메일이 있습니다.
        `;

        console.log(error);
      }
    },
  },
  mounted() {
    if (this.$store.state.loggedIn == true) {
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
