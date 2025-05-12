fetch('/api/projects')
  .then(response => response.json())
  .then(data => {
    const list = document.getElementById('project-list');
    list.innerHTML = ''; // 목록 초기화

    data.forEach(project => {
      const card = document.createElement('div');
      card.className = 'project-card';
      card.innerHTML = `
        <h2>${project.projectTitle}</h2>
        <p><strong>클라이언트:</strong> ${project.clientName}</p>
        <p><strong>필요 스킬:</strong> ${project.requiredSkills.join(', ')}</p>
        <p><strong>등록 날짜:</strong> ${project.time.split('T')[0]}</p>
      `;

      // 프로젝트 클릭 시 모달로 상세 정보 표시
      card.addEventListener('click', () => {
        fetch(`/api/projects/${project.projectIdx}`)
          .then(response => response.json())
          .then(detail => {
            const modalContent = document.getElementById('project-detail-content');
            modalContent.innerHTML = `
              <h3>프로젝트 상세 정보</h3>
              <p><strong>클라이언트:</strong> ${detail.clientName}</p>
              <p><strong>주소:</strong> ${detail.clientAddress}</p>
              <p><strong>전화번호:</strong> ${detail.clientPhone}</p>
              <p><strong>프로젝트명:</strong> ${detail.projectTitle}</p>
              <p><strong>정보:</strong> ${detail.projectInfo}</p>
              <p><strong>예산:</strong> ${detail.projectBudget}</p>
              <p><strong>기간:</strong> ${detail.projectPeriod}</p>
              <p><strong>필요 스킬:</strong> ${detail.requiredSkills.join(', ')}</p>
              <p><strong>등록 날짜:</strong> ${detail.time.replace('T', ' ').substring(0, 16)}</p>
            `;
            document.getElementById('project-modal').style.display = 'block';
            
            // 지원하기 버튼 동작
            const applyBtn = document.getElementById('apply-button');
            applyBtn.onclick = () => {
              if (currentUserType !== '프리랜서') {
                alert('프리랜서만 이용 가능합니다.');
                return;
              }

              selectedProjectTitle = detail.projectTitle;
              selectedProjectId = detail.projectIdx; 
              document.getElementById('apply-confirm-message').textContent = 
                `"${selectedProjectTitle}" 프로젝트에 지원하시겠습니까?`;
              document.getElementById('apply-confirm-modal').style.display = 'block';
            };
          });
      });

      list.appendChild(card);
    });
  });
