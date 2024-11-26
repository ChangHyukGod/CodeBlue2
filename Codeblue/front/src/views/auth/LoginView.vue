<template>
  <div>
    <div class="row justify-content-center">
      <div class="col-xl-6 col-lg-8 col-md-10">
        <div class="card custom-card mt-5">
          <div class="card-body p-0">
            <div class="row">
              <!-- TODO: 입력양식 -->
              <div class="col-lg-12">
                <div class="p-5">
                  <div class="text-center">
                    <h1 class="h4 mb-4">로그인</h1>
                  </div>
                  <div class="user">
                    <div class="form-group">
                      <input
                        type="text"
                        class="form-control form-control-user mb-3"
                        placeholder="이메일을 입력하세요."
                        v-model="user.email"
                      />
                    </div>
                    <div class="form-group">
                      <input
                        type="password"
                        class="form-control form-control-user mb-3"
                        placeholder="주민등록번호를 입력하세요."
                        v-model="user.password"
                      />
                    </div>
                    <button class="btn btn-custom w-100 mb-3" @click="login">
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
                      <a
                        class="small mx-2"
                        href="#"
                        data-bs-toggle="modal"
                        data-bs-target="#findEmailModal"
                      >
                        아이디 찾기
                      </a>
                      <span>|</span>
                      <a
                        class="small mx-2"
                        href="#"
                        data-bs-toggle="modal"
                        data-bs-target="#findPasswordModal"
                      >
                        비밀번호 찾기
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

    <!-- 이메일 찾기 모달 -->
    <div
      class="modal fade"
      id="findEmailModal"
      tabindex="-1"
      aria-labelledby="findEmailModalLabel"
      aria-hidden="true"
    >
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title" id="findEmailModalLabel">아이디 찾기</h5>
            <button
              type="button"
              class="btn-close"
              data-bs-dismiss="modal"
              aria-label="Close"
            ></button>
          </div>
          <div class="modal-body">
            <div class="form-group mb-3">
              <label for="name">이름</label>
              <input
                type="text"
                id="findEmail"
                class="form-control"
                v-model="findEmailData.name"
                placeholder="이름을 입력하세요."
              />
            </div>
            <div class="form-group mb-3">
              <label for="name">주민등록번호</label>
              <input
                type="password"
                id="findEmail"
                class="form-control"
                v-model="findEmailData.ssn"
                placeholder="주민등록번호를 입력하세요."
              />
            </div>
          </div>
          <div class="modal-footer">
            <button
              type="button"
              class="btn btn-secondary"
              data-bs-dismiss="modal"
            >
              닫기
            </button>
            <button type="button" class="btn btn-primary" @click="findEmail">
              확인
            </button>
          </div>
        </div>
      </div>
    </div>

    <!-- 비밀번호 찾기 모달 -->
    <div
      class="modal fade"
      id="findPasswordModal"
      tabindex="-1"
      aria-labelledby="findPasswordModalLabel"
      aria-hidden="true"
    >
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title" id="findPasswordModalLabel">
              비밀번호 찾기
            </h5>
            <button
              type="button"
              class="btn-close"
              data-bs-dismiss="modal"
              aria-label="Close"
            ></button>
          </div>
          <div class="modal-body">
            <div class="form-group mb-3">
              <label for="email">이메일</label>
              <input
                type="text"
                id="findPassword"
                class="form-control"
                v-model="findPasswordData.email"
                placeholder="이메일을 입력하세요."
              />
            </div>
            <div class="form-group">
              <label for="name">주민등록번호</label>
              <input
                type="text"
                id="findPassword"
                class="form-control"
                v-model="findPasswordData.ssn"
                placeholder="이름을 입력하세요."
              />
            </div>
          </div>
          <div class="modal-footer">
            <button
              type="button"
              class="btn btn-secondary"
              data-bs-dismiss="modal"
            >
              닫기
            </button>
            <button type="button" class="btn btn-primary" @click="findPassword">
              확인
            </button>
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

      findEmailData: {
        name: "", // 이름
        ssn: "", // 주민번호
      },

      findPasswordData: {
        email: "", // 이메일
        ssn: "", // 주민번호
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

    async findEmail() {
      try {
        let response = await MemberService.findEmail(this.findEmailData);
        alert(`아이디는 ${response.data}입니다.`);
      } catch (error) {
        console.error(error);
        alert("아이디를 찾을 수 없습니다.");
      }
    },

    async findPassword() {
      try {
        let response = await MemberService.findPassword(this.findPasswordData);
        alert(`비밀번호는 ${response.data}입니다.`);
      } catch (error) {
        console.error(error);
        alert("비밀번호를 찾을 수 없습니다.");
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
/* 에러 메시지 디자인 시작 */
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
/* 에러 메시지 디자인 끝 */

/* 로그인 버튼 디자인 시작 */
.btn-custom {
  background-color: black;
  color: white;
  border-radius: 50px;
  padding: 10px 20px;
  font-size: 16px;
  border: none;
}

.btn-custom:hover,
.btn-custom:focus,
.btn-custom:active {
  background-color: black;
  color: white;
  outline: none;
  box-shadow: none;
}
/* 로그인 버튼 디자인 끝 */

/* 카드의 테두리 색상을 흰색으로 설정 */
.custom-card {
  background-color: #f8f9fa; /* 배경색: 라이트그레이 */
  border: 1px solid white; /* 테두리 흰색 */
  border-radius: 30px; /* 둥근 모서리 */
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1); /* 은은한 그림자 */
}

/* 모달 디자인 시작 */
.modal-content {
  border-radius: 15px;
  padding: 20px;
}
.modal-header {
  border-bottom: none;
}
.modal-footer {
  border-top: none;
}
/* 모달 디자인 끝 */
</style>
