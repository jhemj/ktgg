<template>
  <div id="app">
    <header>
      <h1>프로젝트 관리 시스템</h1>
      <nav>
        <router-link to="/">홈</router-link>
        <router-link to="/staff">직원 페이지</router-link>
        <router-link to="/customer">고객 페이지</router-link>
        <router-link to="/management">관리 페이지</router-link> <!-- 관리 페이지 링크 추가 -->
      </nav>
    </header>
    <main>
      <router-view />
    </main>
  </div>
</template>

<script>
export default {
  name: 'App',
};
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  text-align: center;
  color: #2c3e50;
  margin-top: 30px;
}

header {
  background-color: #f5f5f5;
  padding: 10px;
}

nav {
  margin: 10px 0;
}

nav a {
  margin: 0 10px;
  text-decoration: none;
  color: #42b983;
}

nav a.router-link-exact-active {
  font-weight: bold;
  color: #35495e;
}
</style>