<template>
  <div class="project-management">
    <h1>직원 페이지</h1>

    <!-- 새 프로젝트 만들기 섹션 -->
    <section class="new-project-section">
      <h2>새 프로젝트 만들기</h2>
      <form @submit.prevent="createProject" class="project-form">
        <div class="form-group">
          <label for="projectname">프로젝트 이름</label>
          <input type="text" id="projectname" v-model="newProject.projectname" placeholder="프로젝트 이름" required />
        </div>
        <div class="form-group">
          <label for="year">연도</label>
          <input type="text" id="year" v-model="newProject.year" placeholder="연도" required />
        </div>
        <div class="form-group">
          <label for="scale">규모</label>
          <input type="number" id="scale" v-model="newProject.scale" placeholder="규모" required />
        </div>
        <div class="form-group">
          <label for="target">목표</label>
          <input type="number" id="target" v-model="newProject.target" placeholder="목표" required />
        </div>
        <div class="form-group">
          <label for="host">주최</label>
          <input type="text" id="host" v-model="newProject.host" placeholder="주최" required />
        </div>
        <div class="form-group">
          <label for="summary">요약</label>
          <textarea id="summary" v-model="newProject.summary" placeholder="요약" required></textarea>
        </div>
        <div class="form-group">
          <label for="link">링크</label>
          <input type="text" id="link" v-model="newProject.link" placeholder="링크" />
        </div>
        <div class="form-group date-group">
          <div>
            <label for="startDate">시작 날짜</label>
            <input type="date" id="startDate" v-model="newProject.startDate" />
          </div>
          <div>
            <label for="endDate">종료 날짜</label>
            <input type="date" id="endDate" v-model="newProject.endDate" />
          </div>
        </div>
        <button type="submit" class="btn-submit">프로젝트 생성</button>
      </form>
    </section>

    <!-- 기존 프로젝트 수정 섹션 -->
    <section class="edit-project-section">
      <h2>기존 프로젝트 수정</h2>
      <div class="select-group">
        <label for="selectProject">프로젝트 선택</label>
        <select id="selectProject" v-model="selectedProjectId" @change="fetchProjectDetails">
          <option value="" disabled>프로젝트를 선택하세요</option>
          <option v-for="project in projects" :key="project.id" :value="project.id">
            {{ project.projectname }}
          </option>
        </select>
      </div>

      <form v-if="selectedProject" @submit.prevent="editProject" class="project-form">
        <div class="form-group">
          <label for="editProjectname">프로젝트 이름</label>
          <input type="text" id="editProjectname" v-model="selectedProject.projectname" required />
        </div>
        <div class="form-group">
          <label for="editYear">연도</label>
          <input type="text" id="editYear" v-model="selectedProject.year" required />
        </div>
        <div class="form-group">
          <label for="editScale">규모</label>
          <input type="number" id="editScale" v-model="selectedProject.scale" required />
        </div>
        <div class="form-group">
          <label for="editTarget">목표</label>
          <input type="number" id="editTarget" v-model="selectedProject.target" required />
        </div>
        <div class="form-group">
          <label for="editHost">주최</label>
          <input type="text" id="editHost" v-model="selectedProject.host" required />
        </div>
        <div class="form-group">
          <label for="editSummary">요약</label>
          <textarea id="editSummary" v-model="selectedProject.summary" required></textarea>
        </div>
        <div class="form-group">
          <label for="editLink">링크</label>
          <input type="text" id="editLink" v-model="selectedProject.link" />
        </div>
        <div class="form-group date-group">
          <div>
            <label for="editStartDate">시작 날짜</label>
            <input type="date" id="editStartDate" v-model="selectedProject.startDate" />
          </div>
          <div>
            <label for="editEndDate">종료 날짜</label>
            <input type="date" id="editEndDate" v-model="selectedProject.endDate" />
          </div>
        </div>
        <button type="submit" class="btn-submit">프로젝트 수정</button>
      </form>
    </section>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  name: 'ProjectManagement',
  data() {
    return {
      newProject: {
        projectname: '',
        year: '',
        scale: '',
        target: '',
        host: '',
        summary: '',
        link: '',
        startDate: '',
        endDate: '',
      },
      projects: [],
      selectedProjectId: '',
      selectedProject: null,
    };
  },
  methods: {
    async createProject() {
      try {
        await axios.post(`${process.env.VUE_APP_API_URL}/projects`, this.newProject);
        alert('프로젝트가 생성되었습니다!');
        this.fetchProjects();
        this.resetNewProject();
      } catch (error) {
        console.error(error);
        alert('프로젝트 생성 중 오류가 발생했습니다.');
      }
    },
    async fetchProjects() {
      try {
        const response = await axios.get(`${process.env.VUE_APP_API_URL}/projects/all`);
        this.projects = response.data;
      } catch (error) {
        console.error('Error fetching projects:', error);
      }
    },
    async fetchProjectDetails() {
      if (this.selectedProjectId) {
        try {
          const response = await axios.get(`${process.env.VUE_APP_API_URL}/projects/${this.selectedProjectId}`);
          this.selectedProject = response.data;
        } catch (error) {
          console.error('Error fetching project details:', error);
        }
      }
    },
    async editProject() {
      try {
        await axios.patch(`${process.env.VUE_APP_API_URL}/projects/${this.selectedProjectId}/edit`, this.selectedProject);
        alert('프로젝트가 수정되었습니다!');
        this.fetchProjects();
        this.resetSelectedProject();
      } catch (error) {
        console.error(error);
        alert('프로젝트 수정 중 오류가 발생했습니다.');
      }
    },
    resetNewProject() {
      this.newProject = {
        projectname: '',
        year: '',
        scale: '',
        target: '',
        host: '',
        summary: '',
        link: '',
        startDate: '',
        endDate: '',
      };
    },
    resetSelectedProject() {
      this.selectedProjectId = '';
      this.selectedProject = null;
    },
  },
  mounted() {
    this.fetchProjects();
  },
};
</script>

<style scoped>
.project-management {
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

section {
  background: #f9f9f9;
  padding: 20px 30px;
  border-radius: 8px;
  margin-bottom: 30px;
  box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
}

h2 {
  color: #34495e;
  margin-bottom: 20px;
  border-bottom: 2px solid #ecf0f1;
  padding-bottom: 10px;
}

.project-form {
  display: flex;
  flex-direction: column;
}

.form-group {
  display: flex;
  flex-direction: column;
  margin-bottom: 15px;
}

.form-group label {
  margin-bottom: 5px;
  font-weight: bold;
  color: #34495e;
}

.form-group input,
.form-group textarea,
.select-group select {
  padding: 10px;
  border: 1px solid #bdc3c7;
  border-radius: 4px;
  font-size: 14px;
}

.form-group input:focus,
.form-group textarea:focus,
.select-group select:focus {
  border-color: #3498db;
  outline: none;
}

.date-group {
  display: flex;
  justify-content: space-between;
}

.date-group > div {
  width: 48%;
}

.btn-submit {
  padding: 12px 20px;
  background-color: #3498db;
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  font-size: 16px;
  align-self: flex-start;
}

.btn-submit:hover {
  background-color: #2980b9;
}

.select-group {
  display: flex;
  flex-direction: column;
  margin-bottom: 20px;
}

.select-group label {
  margin-bottom: 5px;
  font-weight: bold;
  color: #34495e;
}

.select-group select {
  padding: 10px;
  border: 1px solid #bdc3c7;
  border-radius: 4px;
  font-size: 14px;
}

.select-group select:focus {
  border-color: #3498db;
  outline: none;
}

@media (max-width: 600px) {
  .date-group {
    flex-direction: column;
  }

  .date-group > div {
    width: 100%;
    margin-bottom: 15px;
  }

  .btn-submit {
    width: 100%;
    text-align: center;
  }
}
</style>