<template>
    <div class="customer-page">
      <h1>고객 페이지</h1>
  
      <!-- 고객 전화번호 입력 필드 -->
      <section class="form-section">
        <label for="phone" class="form-label">고객 전화번호:</label>
        <input type="text" id="phone" v-model="customerPhone" placeholder="전화번호를 입력하세요" class="form-input" />
      </section>
  
      <!-- 전체 프로젝트 리스트 -->
      <section class="project-list-section">
        <h2>전체 프로젝트 리스트</h2>
        <ul v-if="projects.length > 0" class="project-list">
          <li v-for="project in projects" :key="project.id" class="project-item">
            <div>
              <h3>{{ project.projectname }}</h3>
              <p v-if="project.summary">{{ project.summary }}</p>
              <p v-if="project.year || project.scale">
                <span v-if="project.year">연도: {{ project.year }}</span>
                <span v-if="project.year && project.scale">, </span>
                <span v-if="project.scale">규모: {{ project.scale }}</span>
              </p>
  
              <!-- 관심 설정 체크박스 -->
              <label class="checkbox-label">
                <input type="checkbox" v-model="project.interest" @change="updateInterest(project)" />
                관심 설정
              </label>
  
              <!-- 컨설팅 신청 버튼 -->
              <button @click="openConsultationModal(project)" class="btn-apply">컨설팅 신청</button>
            </div>
          </li>
        </ul>
        <p v-else>프로젝트가 없습니다.</p>
      </section>
  
      <!-- 컨설팅 신청 모달 -->
      <div v-if="showConsultationModal" class="modal">
        <div class="modal-content">
          <span class="close" @click="closeConsultationModal">&times;</span>
          <h2>컨설팅 신청</h2>
          <textarea v-model="consultationMemo" placeholder="메모를 입력하세요" class="form-textarea"></textarea>
          <input type="date" v-model="consultationDate" class="form-input" />
          <button @click="createConsultation" class="btn-apply">신청하기</button>
        </div>
      </div>
    </div>
  </template>
  
  <script>
  import axios from 'axios';
  
  export default {
    data() {
      return {
        customerPhone: '',
        projects: [],
        showConsultationModal: false,
        consultationMemo: '',
        consultationDate: '',
        selectedProject: null,
      };
    },
    methods: {
      async fetchProjects() {
        try {
          const response = await axios.get(`${process.env.VUE_APP_API_URL}/projects/all`);
          this.projects = response.data;
        } catch (error) {
          console.error('Error fetching projects:', error);
        }
      },
      updateInterest(project) {
        const interestData = {
          projectname: project.projectname,
          phone: this.customerPhone,
          interest: project.interest,
          date: new Date(),
        };
  
        if (project.interest) {
          axios
            .post(`${process.env.VUE_APP_API_URL}/interests`, interestData)
            .then(() => alert('관심이 등록되었습니다!'))
            .catch((error) => console.error(error));
        } else {
          axios
            .get(`${process.env.VUE_APP_API_URL}/interests?phone=${this.customerPhone}&projectname=${project.projectname}`)
            .then((response) => {
              const interest = response.data[0];
              if (interest) {
                interest.interest = false;
                axios
                  .patch(`${process.env.VUE_APP_API_URL}/interests/${interest.id}`, interest)
                  .then(() => alert('관심이 해제되었습니다!'))
                  .catch((error) => console.error(error));
              }
            })
            .catch((error) => console.error(error));
        }
      },
      openConsultationModal(project) {
        this.selectedProject = project;
        this.showConsultationModal = true;
      },
      closeConsultationModal() {
        this.showConsultationModal = false;
        this.consultationMemo = '';
        this.consultationDate = '';
      },
      createConsultation() {
        const consultationData = {
          projectname: this.selectedProject.projectname,
          phone: this.customerPhone,
          consultationdate: this.consultationDate,
          customername: this.customerPhone,
          memo: this.consultationMemo,
        };
  
        axios
          .post(`${process.env.VUE_APP_API_URL}/consultations`, consultationData)
          .then(() => {
            alert('컨설팅 신청이 완료되었습니다!');
            this.closeConsultationModal();
          })
          .catch((error) => console.error(error));
      },
    },
    mounted() {
      this.fetchProjects();
    },
  };
  </script>
  
  <style scoped>
  .customer-page {
    max-width: 800px;
    margin: 0 auto;
    padding: 20px;
    font-family: Arial, sans-serif;
  }
  
  h1 {
    text-align: center;
    color: #2c3e50;
    margin-bottom: 40px;
  }
  
  .form-section {
    margin-bottom: 30px;
  }
  
  .form-label {
    display: block;
    font-weight: bold;
    margin-bottom: 5px;
    color: #34495e;
  }
  
  .form-input,
  .form-textarea {
    width: 100%;
    padding: 10px;
    border: 1px solid #bdc3c7;
    border-radius: 4px;
    font-size: 14px;
    margin-bottom: 15px;
  }
  
  .form-input:focus,
  .form-textarea:focus {
    border-color: #3498db;
    outline: none;
  }
  
  .project-list {
    list-style-type: none;
    padding: 0;
  }
  
  .project-item {
    background-color: #f9f9f9;
    border: 1px solid #e0e0e0;
    border-radius: 5px;
    margin: 10px 0;
    padding: 15px;
    box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
    transition: transform 0.2s;
  }
  
  .project-item:hover {
    transform: scale(1.02);
  }
  
  .checkbox-label {
    display: flex;
    align-items: center;
    margin-top: 10px;
  }
  
  .btn-apply {
    background-color: #3498db;
    color: white;
    padding: 10px 15px;
    border: none;
    border-radius: 4px;
    cursor: pointer;
    font-size: 14px;
    margin-top: 10px;
    transition: background-color 0.3s;
  }
  
  .btn-apply:hover {
    background-color: #2980b9;
  }
  
  .modal {
    display: flex;
    position: fixed;
    z-index: 1;
    left: 0;
    top: 0;
    width: 100%;
    height: 100%;
    background-color: rgba(0, 0, 0, 0.5);
    justify-content: center;
    align-items: center;
  }
  
  .modal-content {
    background-color: white;
    padding: 20px;
    border-radius: 8px;
    width: 400px;
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
    position: relative;
  }
  
  .close {
    position: absolute;
    top: 10px;
    right: 20px;
    font-size: 24px;
    cursor: pointer;
  }
  
  .close:hover {
    color: #e74c3c;
  }
  </style>