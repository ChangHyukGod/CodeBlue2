// LoginView.vue
<template>
  <div>
    <div class="row justify-content-center">
      <div class="col-xl-10 col-lg-12 col-md-9">
        <div class="card mt-5">
          <div class="card-body p-0">
            <!-- {/* Nested Row within Card Body */} -->
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
                    <h1 class="h4 mb-4">Welcome Back!</h1>
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
                  </div>
                  <hr />
                  <div class="text-center">
                    <a class="small" href="/register"> Create an Account! </a>
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
    };
  },
  methods: {
    async login() {
      try {
        let response = await MemberService.login(this.user);
        console.log(response.data);

        this.$store.state.loggedIn = true; 
    
        localStorage.setItem("user", JSON.stringify(response.data));

        this.$router.push("/");
      } catch (error) {
        this.$store.state.loggedIn == false; 
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

<style></style>
