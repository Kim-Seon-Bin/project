function join() {
    const user = {
      userId: document.getElementById("userId").value,
      userPw: document.getElementById("userPw").value,
      userEmail: document.getElementById("userEmail").value,
      userType: document.getElementById("userType").value
    };
  
    fetch('/api/users/join', {
      method: 'POST',
      headers: {'Content-Type': 'application/json'},
      body: JSON.stringify(user)
    })
    .then(res => res.text())
    .then(msg => alert(msg));
  }
  
  function login() {
    const userId = document.getElementById("loginId").value;
    const userPw = document.getElementById("loginPw").value;
  
    fetch(`/api/users/login?userId=${userId}&userPw=${userPw}`, {
      method: 'POST'
    })
    .then(res => res.json())
    .then(success => {
      if (success) alert("로그인 성공");
      else alert("로그인 실패");
    });
  }
  