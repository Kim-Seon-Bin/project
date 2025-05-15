function loadUserInfoAndInitPage() {
  fetch('/api/user/info', {
    method: 'GET',
    credentials: 'same-origin'
  })
  .then(res => res.json())
  .then(data => {
    document.getElementById('user-id').textContent = data.userId || '';
    document.getElementById('user-email').textContent = data.userEmail || '';
    document.getElementById('user-type').textContent = data.userType || '';

    if (data.userType === '프리랜서') {
      const script = document.createElement('script');
      script.src = 'mypage-freelancer.js';
      document.body.appendChild(script);
    } else if (data.userType === '클라이언트') {
      const script = document.createElement('script');
      script.src = 'mypage-client.js';
      document.body.appendChild(script);
    }
  })
  .catch(err => {
    console.error('사용자 정보 불러오기 실패:', err);
    alert('로그인이 필요합니다.');
    location.href = 'login.html';
  });
}
