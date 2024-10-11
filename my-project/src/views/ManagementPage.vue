<template>
    <div>
      <h1>관리 페이지</h1>
      <table>
        <thead>
          <tr>
            <th>ID</th>
            <th>프로젝트 이름</th>
            <th>전화번호</th>
            <th>고객 이름</th>
            <th>매칭된 영업사원</th>
            <th>메모</th>
            <th>단계</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="consultation in consultations" :key="consultation.id">
            <td>{{ consultation.id }}</td>
            <td>{{ consultation.projectname }}</td>
            <td>{{ consultation.phone }}</td>
            <td>{{ consultation.customername }}</td>
            <td>{{ consultation.matchedsalesman || '미매칭' }}</td>
            <td>{{ consultation.memo }}</td>
            <td>
              <select v-model="consultation.step" @change="updateStep(consultation)">
                <option disabled value="">단계 선택</option>
                <option value="apply">Apply</option>
                <option value="accept">Accept</option>
                <option value="canceled">Canceled</option>
                <option value="success">Success</option>
                <option value="fail">Fail</option>
              </select>
            </td>
          </tr>
        </tbody>
      </table>
  
      <div v-if="loading" class="loading">
        로딩 중...
      </div>
  
      <div v-if="error" class="error">
        오류 발생: {{ error }}
      </div>
    </div>
  </template>
  
  <script>
  import axios from 'axios';
  
  export default {
    name: 'ManagementPage',
    data() {
      return {
        consultations: [],
        loading: false,
        error: null,
      };
    },
    methods: {
      async fetchConsultations() {
        this.loading = true;
        this.error = null;
        try {
          const response = await axios.get(`${process.env.VUE_APP_API_URL}/consultations`);
          this.consultations = response.data;
        } catch (error) {
          this.error = error.response ? error.response.data : error.message;
          console.error('Error fetching consultations:', error);
        } finally {
          this.loading = false;
        }
      },
      async updateStep(consultation) {
        try {
          await axios.patch(`${process.env.VUE_APP_API_URL}/consultations/${consultation.id}/step`, null, {
            params: { step: consultation.step },
          });
          alert(`컨설팅 ID ${consultation.id}의 단계가 "${consultation.step}"으로 변경되었습니다.`);
        } catch (error) {
          this.error = error.response ? error.response.data : error.message;
          console.error('Error updating consultation step:', error);
          alert('단계 변경 중 오류가 발생했습니다.');
        }
      },
    },
    mounted() {
      this.fetchConsultations();
    },
  };
  </script>
  
  <style scoped>
  table {
    width: 100%;
    border-collapse: collapse;
    margin-top: 20px;
  }
  
  thead {
    background-color: #f5f5f5;
  }
  
  th, td {
    border: 1px solid #ddd;
    padding: 8px;
    text-align: center;
  }
  
  th {
    font-weight: bold;
  }
  
  .loading {
    margin-top: 20px;
    font-size: 18px;
    color: #42b983;
  }
  
  .error {
    margin-top: 20px;
    font-size: 18px;
    color: red;
  }
  </style>