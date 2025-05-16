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

function showProjectModal(detail) {
  const content = `
    <h3>프로젝트 상세 정보</h3>
    <p><strong>클라이언트:</strong></p> 
    <p><strong>주소:</strong> </p> 
    <p><strong>전화번호:</strong> </p>
    <p><strong>프로젝트명:</strong> </p> 
    <p><strong>정보:</strong> </p> 
    <p><strong>예산:</strong> </p>
    <p><strong>기간:</strong></p> 
    <p><strong>필요 스킬:</strong> </p>
    <p><strong>등록 날짜:</strong> </p>
  `;
  document.getElementById('project-detail-content').innerHTML = content;
  document.getElementById('project-modal').style.display = 'block';

  window.onclick = function(event) {
    const modal = document.getElementById('project-modal');
    if (event.target == modal) {
      modal.style.display = 'none';
    }
  };
}