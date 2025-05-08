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
      `;

      // 프로젝트 클릭 시 모달로 상세 정보 표시
      card.addEventListener('click', () => {
        fetch(`/api/projects/${project.projectIdx}`)  // DTO에 projectId 필드 있어야 함
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
            `;
            document.getElementById('project-modal').style.display = 'block';
          });
      });

      list.appendChild(card);
    });
  });
